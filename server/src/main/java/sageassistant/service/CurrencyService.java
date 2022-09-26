/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:44:32
 * @FilePath       : \server\src\main\java\sageassistant\service\CurrencyService.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sageassistant.dao.CurrencyMapper;
import sageassistant.model.CurrencyHistory;

@Service
public class CurrencyService {

    private static final Logger log = LogManager.getLogger();

    private static HashMap<String, String> dafaultRate = new HashMap<String, String>();
    static HttpService httpService;

    @Autowired
    private CurrencyMapper currencyMapper;

    @Value("${currency.from}")
    private String currencyFrom;

    /**
     * google guava cache
     */
    public LoadingCache<String, String> cache = CacheBuilder
        .newBuilder()
        .build(
            new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    return currencyFrom.equals("sage") ? getFromSage(key) : getFromUrl(key);
                }
            }
        );

    public CurrencyService() {
        dafaultRate.put("EURUSD", "1.18");
        dafaultRate.put("GBPUSD", "1.31");
        dafaultRate.put("SGDUSD", "0.73");
        dafaultRate.put("RMBUSD", "0.145");
        dafaultRate.put("HKDUSD", "0.13");
        dafaultRate.put("MXNUSD", "0.064");
        dafaultRate.put("AEDUSD", "0.27");
        dafaultRate.put("QARUSD", "0.275");
        dafaultRate.put("CADUSD", "0.7639");
        dafaultRate.put("AUDUSD", "0.7285");
        dafaultRate.put("JPYUSD", "0.00962");
    }

    /*
     * key like RMBUSD2010-10-10, if not find, return "0"
     */
    public String getCurrencyRate(String key) {
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
    public String getCurrencyRateBatch(String key) {
        try {
            String[] q = key.split("_");
            JSONArray all = new JSONArray();

            for (int i = 0, l = q.length; i < l; i++) {
                log.info(q[i]);
                all.add(cache.get(q[i]));
            }
            return all.toJSONString();
        } catch (ExecutionException e) {
            log.error(key);
            return "0";
        }
    }

    /*
     * key like RMBUSD2010-10-10, if not find, return "0"
     */
    private String getFromUrl(String key) {
        if (key.length() != 16) {
            return "0";
        }

        String Sour = key.substring(0, 3);
        if (Sour.equals("CNY")) {
            Sour = "RMB";
        }
        String Dest = key.substring(3, 6);
        String Date = key.substring(6, 16);

        if (Sour.equals(Dest)) {
            return "1";
        }

        float rate = 0;
        float rateRMBUSD = 0;

        // get from 'State Administration of Foreign Exchange'
        String responseText = HttpService.getRequestResult(
            "http://www.safe.gov.cn/AppStructured/hlw/ENJsonRmb.do?date=" + Date,
            "get",
            null
        );
        if (responseText.equals("")) {
            responseText = "";
        }
        log.debug("responseText:" + responseText);
        // responseText like [[100,"USD","694.97","RMB"],[100,"EUR","771.95","RMB"]]
        JSONArray jsonArrayOuter = JSONArray.parseArray(responseText);

        // rate default is RMB
        for (int i = 0, l = jsonArrayOuter.size(); i < l; i++) {
            JSONArray jsonArrayInner = jsonArrayOuter.getJSONArray(i);

            // get RMBUSD, it could used for others currency
            if (jsonArrayInner.getString(1).equals("USD") && jsonArrayInner.getString(3).equals("RMB")) {
                rateRMBUSD = jsonArrayInner.getIntValue(0) / jsonArrayInner.getFloat(2);
                rate = rateRMBUSD;
                log.debug("rateRMBUSD:" + rateRMBUSD);
            }

            // calulate by RMBUSD for other currency
            if (jsonArrayInner.getString(1).equals(Sour) && jsonArrayInner.getString(3).equals("RMB")) {
                rate = jsonArrayInner.getFloat(2) / jsonArrayInner.getIntValue(0) * rateRMBUSD;
                log.debug("rate1:" + rate);
                break;
            }
            // calulate by RMBUSD for other currency
            if (jsonArrayInner.getString(1).equals("RMB") && jsonArrayInner.getString(3).equals(Sour)) {
                // backup of if
                rate = jsonArrayInner.getIntValue(0) / jsonArrayInner.getFloat(2) * rateRMBUSD;
                log.debug("rate2:" + rate);
                break;
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

    public String getFromSage(String key) {
        if (key.length() != 16) {
            return "0";
        }

        String Sour = key.substring(0, 3);
        if (Sour.equals("CNY")) {
            Sour = "RMB";
        }
        String Dest = key.substring(3, 6);
        String Date = key.substring(6, 16);

        if (Sour.equals(Dest)) {
            return "1";
        }

        List<CurrencyHistory> list = currencyMapper.findCurrencyRate(Sour, Dest, Date);

        String rateStr = "0";
        if (list.size() == 0 && dafaultRate.containsKey(Sour + Dest)) {
            rateStr = dafaultRate.get(Sour + Dest);
        } else {
            rateStr = list.get(0).getRate().toString();
        }
        return rateStr;
    }
}
