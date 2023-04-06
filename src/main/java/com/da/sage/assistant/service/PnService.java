/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:57:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:24:06                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/service/PnService.java                               *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.service;

import com.da.sage.assistant.dao.PnMapper;
import com.da.sage.assistant.dao.StockMapper;
import com.da.sage.assistant.model.CostHistory;
import com.da.sage.assistant.model.DeliveryDuration;
import com.da.sage.assistant.model.PnDetails;
import com.da.sage.assistant.model.PnRootPn;
import com.da.sage.assistant.model.PnStatus;
import com.da.sage.assistant.model.QuoteHistory;
import com.da.sage.assistant.model.SalesHistory;
import com.da.sage.assistant.model.StockInfo;
import com.da.sage.assistant.utils.Utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<PnStatus> findObsoletePnBySite(String Site) {
        return pnMapper.findObsoletePnBySite(Site);
    }
}
