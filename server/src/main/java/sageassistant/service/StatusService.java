/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:45:12
 * @FilePath       : \server\src\main\java\sageassistant\service\StatusService.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sageassistant.dao.StatusMapper;
import sageassistant.model.TobeClosedWO;
import sageassistant.model.TobeDealWithOrderLine;
import sageassistant.model.TobeDelivery;
import sageassistant.model.TobePurchaseBom;
import sageassistant.model.TobeReceive;
import sageassistant.model.TobeTracking;
import sageassistant.utils.Utils;

@Service
public class StatusService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private CurrencyService currencyService;

    public List<TobeDelivery> findTobeDeliveryBySite(String site, Integer count) {
        List<TobeDelivery> listPage = statusMapper.findTobeDeliveryBySite(site, count);

        for (TobeDelivery o : listPage) {
            // log.debug("ooo:" + o.toString());
            String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
            // log.debug("key:" + key);
            try {
                o.setRate(Float.parseFloat(currencyService.cache.get(key)));
                // log.debug("Rate:" + o.getRate());
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
            } catch (ExecutionException e) {
                log.error(e.getMessage());
            }
            // o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
        }

        return listPage;
    }

    public List<TobeReceive> findTobeReceiveBySite(String site, Integer count) {
        List<TobeReceive> listPage = statusMapper.findTobeReceiveBySite(site, count);

        for (TobeReceive o : listPage) {
            String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
            // log.debug("key:" + key);
            try {
                o.setRate(Float.parseFloat(currencyService.cache.get(key)));
                // log.debug("Rate:" + o.getRate());
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
            } catch (ExecutionException e) {
                log.error(e.getMessage());
            }
            o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
        }

        return listPage;
    }

    public List<TobeDealWithOrderLine> findTobeDealWithOrderLineBySite(String site, Integer count) {
        List<TobeDealWithOrderLine> listPage = statusMapper.findTobeDealWithOrderLineBySite(site, count);

        return listPage;
    }

    public List<TobePurchaseBom> findTobePurchaseBomBySite(String site, Integer count) {
        List<TobePurchaseBom> listPage = statusMapper.findTobePurchaseBomBySite(site, count);

        return listPage;
    }

    public List<TobeClosedWO> findTobeClosedWOBySite(String site, Integer count) {
        List<TobeClosedWO> listPage = statusMapper.findTobeClosedWOBySite(site, count);

        return listPage;
    }

    public List<TobeTracking> findTobeTracking(String site) {
        List<TobeTracking> listPage = statusMapper.findTobeTracking(site);

        return listPage;
    }
}
