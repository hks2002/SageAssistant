package sageassistant;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import net.sf.json.JSONArray;

@CrossOrigin
@RestController
public class CurrencyController {
	private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);

	private HashMap<String, String> dafaultRate = new HashMap<String, String>();

	/**
	 * google guava cache
	 */
	private LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
		@Override
		public String load(String key) {
			return getFromUrl(key);
		}
	});

	CurrencyController() {
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

	private String doGet(String path) {
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

	private String getFromUrl(String key) {
		log.debug("key:" + key);
		String CurrencySour = key.substring(0, 3);
		String CurrencyDest = key.substring(3, 6);
		String Date = key.substring(6, 16);

		double rate = 0;

		if (CurrencyDest.equals("RMB")) { // get from 'State Administration of Foreign Exchange'
			String responseText = doGet("http://www.safe.gov.cn/AppStructured/hlw/ENJsonRmb.do?date=" + Date);
			JSONArray jsonARRAY = JSONArray.fromObject(responseText);

			for (Object obj : jsonARRAY) {
				JSONArray jsonArray = JSONArray.fromObject(obj);
				if (jsonArray.getString(1).equals(CurrencySour)) {
					rate = jsonArray.getDouble(2) / jsonArray.getInt(0);
					break;
				} else if (jsonArray.getString(3).equals(CurrencySour)) {
					rate = jsonArray.getDouble(2) / jsonArray.getInt(0);
					break;
				}
			}
		}

		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		String rateStr = nf.format(rate);

		if (rateStr.equals("0")) {
			rateStr = dafaultRate.get(CurrencySour + CurrencyDest);
		}
		return rateStr;
	}

	@GetMapping("/Data/Currency")
	public String getCurrency(@RequestParam(value = "Date", required = true) String Date,
			@RequestParam(value = "CurrencySour", required = true) String CurrencySour,
			@RequestParam(value = "CurrencyDest", required = false, defaultValue = "RMB") String CurrencyDest) {
		try {
			return cache.get(CurrencySour + CurrencyDest + Date);
		} catch (ExecutionException e) {
			log.error("ExecutionException :" + "When getting Currency for " + CurrencySour + "-->" + CurrencyDest + " "
					+ Date);
			return "0";
		}
	}
}
