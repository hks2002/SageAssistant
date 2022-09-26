/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:44:58
 * @FilePath       : \server\src\main\java\sageassistant\service\PnService.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sageassistant.dao.PnMapper;
import sageassistant.dao.StockMapper;
import sageassistant.model.CostHistory;
import sageassistant.model.DeliveryDuration;
import sageassistant.model.PnDetails;
import sageassistant.model.PnRootPn;
import sageassistant.model.PnStatus;
import sageassistant.model.QuoteHistory;
import sageassistant.model.SalesHistory;
import sageassistant.model.StockInfo;
import sageassistant.utils.Utils;

@Service
public class PnService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private PnMapper pnMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private CurrencyService currencyService;

    public List<PnRootPn> findPnByStartWith(String cond, Integer Count) {
        List<PnRootPn> listPage = pnMapper.findPnByLike(cond + "%", Count);

        return listPage;
    }

    public List<PnRootPn> findPnByEndWith(String cond, Integer Count) {
        List<PnRootPn> listPage = pnMapper.findPnByLike("%" + cond, Count);

        return listPage;
    }

    public List<PnRootPn> findPnByContains(String cond, Integer Count) {
        List<PnRootPn> listPage = pnMapper.findPnByLike("%" + cond + "%", Count);

        return listPage;
    }

    public List<PnDetails> findAllPnByPnRoot(String PnRoot) {
        return pnMapper.findAllPnByPnRoot(PnRoot);
    }

    public List<SalesHistory> findSalesHistoryByPnRoot(String PnRoot) {
        List<SalesHistory> listPage = (ArrayList<SalesHistory>) pnMapper.findSalesHistoryByPnRoot(PnRoot);

        for (SalesHistory o : listPage) {
            String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
            log.debug("key:" + key);
            try {
                o.setRate(Float.parseFloat(currencyService.cache.get(key)));
                log.debug("Rate:" + o.getRate());
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
            } catch (ExecutionException e) {
                log.error(e.getMessage());
            }
            o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
        }

        return listPage;
    }

    public List<QuoteHistory> findQuoteHistoryByPnRoot(String PnRoot) {
        List<QuoteHistory> listPage = pnMapper.findQuoteHistoryByPnRoot(PnRoot);

        for (QuoteHistory o : listPage) {
            String key = o.getCurrency() + "USD" + Utils.formatDate(o.getQuoteDate());
            log.debug("key:" + key);
            try {
                o.setRate(Float.parseFloat(currencyService.cache.get(key)));
                log.debug("Rate:" + o.getRate());
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
            } catch (ExecutionException e) {
                log.error(e.getMessage());
            }
            o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
        }

        return listPage;
    }

    public List<CostHistory> findCostHistoryByPnRoot(String PnRoot) {
        List<CostHistory> listPage = pnMapper.findCostHistoryByPnRoot(PnRoot);

        for (CostHistory o : listPage) {
            String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
            log.debug("key:" + key);
            try {
                o.setRate(Float.parseFloat(currencyService.cache.get(key)));
                log.debug("Rate:" + o.getRate());
            } catch (NumberFormatException e) {
                log.error(e.getMessage());
            } catch (ExecutionException e) {
                log.error(e.getMessage());
            }
            o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
        }
        // one project maybe purchase line with different currency

        return listPage;
    }

    public List<DeliveryDuration> findDeliveryDurationByPnRoot(String PnRoot) {
        return pnMapper.findDeliveryDurationByPnRoot(PnRoot);
    }

    public List<StockInfo> findStockInfoByPnRoot(String PnRoot) {
        return stockMapper.findStockInfoByPnRoot(PnRoot);
    }

    public List<PnStatus> findObseletPnBySite(String Site) {
        return pnMapper.findObseletPnBySite(Site);
    }
}
