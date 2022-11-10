/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-11-10 16:25:41
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
import sageassistant.model.TobeTrackingBOMLine;
import sageassistant.model.TobeTrackingPurchaseOrderLine;
import sageassistant.model.TobeTrackingQALine;
import sageassistant.model.TobeTrackingReceiptLine;
import sageassistant.model.TobeTrackingSalesOrderLine;
import sageassistant.utils.Utils;

@Service
public class StatusService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private CurrencyService currencyService;

    public List<TobeDelivery> findTobeDeliveryBySite(String Site) {
        List<TobeDelivery> listPage = statusMapper.findTobeDeliveryBySite(Site);

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

    public List<TobeReceive> findTobeReceiveBySite(String Site) {
        List<TobeReceive> listPage = statusMapper.findTobeReceiveBySite(Site);

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

    public List<TobeDealWithOrderLine> findTobeDealWithOrderLineBySite(String Site) {
        List<TobeDealWithOrderLine> listPage = statusMapper.findTobeDealWithOrderLineBySite(Site);

        return listPage;
    }

    public List<TobePurchaseBom> findTobePurchaseBomBySite(String Site) {
        List<TobePurchaseBom> listPage = statusMapper.findTobePurchaseBomBySite(Site);

        return listPage;
    }

    public List<TobeClosedWO> findTobeClosedWOBySite(String Site) {
        List<TobeClosedWO> listPage = statusMapper.findTobeClosedWOBySite(Site);

        return listPage;
    }

    public List<TobeTrackingSalesOrderLine> findTobeTrackingSalesOrderLine(String Site, Integer Offset, Integer Limit) {
        List<TobeTrackingSalesOrderLine> listPage = statusMapper.findTobeTrackingSalesOrderLineBySite(
            Site,
            Offset,
            Limit
        );

        return listPage;
    }

    public List<TobeTrackingBOMLine> findTobeTrackingBOMLine(String Site, Integer Offset, Integer Limit) {
        List<TobeTrackingBOMLine> listPage = statusMapper.findTobeTrackingBOMLineBySite(Site, Offset, Limit);

        return listPage;
    }

    public List<TobeTrackingPurchaseOrderLine> findTobeTrackingPurchaseOrderLine(
        String Site,
        Integer Offset,
        Integer Limit
    ) {
        List<TobeTrackingPurchaseOrderLine> listPage = statusMapper.findTobeTrackingPurchaseOrderLineBySite(
            Site,
            Offset,
            Limit
        );

        return listPage;
    }

    public List<TobeTrackingReceiptLine> findTobeTrackingReceiptLine(String Site, Integer Offset, Integer Limit) {
        List<TobeTrackingReceiptLine> listPage = statusMapper.findTobeTrackingReceiptLineBySite(Site, Offset, Limit);

        return listPage;
    }

    public List<TobeTrackingQALine> findTobeTrackingQALine(String Site, Integer Offset, Integer Limit) {
        List<TobeTrackingQALine> listPage = statusMapper.findTobeTrackingQALineBySite(Site, Offset, Limit);

        return listPage;
    }
}
