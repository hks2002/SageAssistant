package sageassistant.service;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class CurrencyService {
	private static final Logger log = LogManager.getLogger();

	private static HashMap<String, String> dafaultRate = new HashMap<String, String>();

	/**
	 * google guava cache
	 */
	public static LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
		@Override
		public String load(String key) {
			return getFromUrl(key);
		}
	});
	
	public CurrencyService() {
		dafaultRate.put("USDRMB", "7.0");
		dafaultRate.put("EURRMB", "7.5");
		dafaultRate.put("HKDRMB", "0.9");
		dafaultRate.put("GBPRMB", "9.0");
		dafaultRate.put("AUDRMB", "4.6");
		dafaultRate.put("JPYRMB", "0.06");
		dafaultRate.put("SDGRMB", "5.0");
		dafaultRate.put("AEDRMB", "0.5");
		dafaultRate.put("CADRMB", "5.2");
	}
	
	/*
	 * key like RMBUSD2010-10-10, if not find, return "0" 
	 */
	public static String getCurrencyRate(String key) {
		try {
			return cache.get(key);
		} catch (ExecutionException e) {
			log.error(key);
			return "0";
		}
	}
	
	/*
	 * key like RMBUSD2010-10-10_RMBUSD2010-10-11_RMBUSD2010-10-12, if not find, return "0" 
	 */
	public static String getCurrencyRateBatch(String key) {
		try {
			String[] q=key.split("_");
			JSONArray all = new JSONArray();
			
			for (int i=0, l=q.length; i<l;i++) {
				log.info(q[i]);
				all.add(cache.get(q[i]));
			}
			return all.toJSONString();
		} catch (ExecutionException e) {
			log.error(key);
			return "0";
		}	
	}
	
	private static String doGet(String path) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(path);

		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				return EntityUtils.toString(responseEntity);
			}
		} catch (ClientProtocolException e) {
			log.error(e.getMessage());
		} catch (ParseException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return "[]";
	}
	
	/*
	 * key like RMBUSD2010-10-10, if not find, return "0"
	 */
	private static String getFromUrl(String key) {
		log.debug("key:" + key);
		
		if (key.length() != 16) {
			return "0";
		}
		
		String Sour = key.substring(0, 3);
		String Dest = key.substring(3, 6);
		String Date =  key.substring(6, 16);
		
		if (Sour.equals(Dest)) {
			return "1";
		}

		float rate = 0;

		if (Dest.equals("RMB")) { // get from 'State Administration of Foreign Exchange'
			String responseText = doGet("http://www.safe.gov.cn/AppStructured/hlw/ENJsonRmb.do?date=" + Date);
			// responseText like [[100,"USD","694.97","RMB"],[100,"EUR","771.95","RMB"]]
			JSONArray jsonArrayOuter = JSONArray.parseArray(responseText);

			for (int i = 0, l = jsonArrayOuter.size(); i < l; i++) {
				JSONArray jsonArrayInner = jsonArrayOuter.getJSONArray(i);

				if (jsonArrayInner.getString(1).equals(Sour)) {
					rate = jsonArrayInner.getFloat(2) / jsonArrayInner.getIntValue(0);
					break;
				} else if (jsonArrayInner.getString(3).equals(Sour)) {
					rate = jsonArrayInner.getFloat(2) / jsonArrayInner.getIntValue(0);
					break;
				}
			}
		}

		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		String rateStr = nf.format(rate);

		if (rateStr.equals("0") && dafaultRate.containsKey(Sour + Dest)) {
			rateStr = dafaultRate.get(Sour + Dest);
		}
		return rateStr;
	}
}
