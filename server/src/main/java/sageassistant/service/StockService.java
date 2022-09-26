/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:45:17
 * @FilePath       : \server\src\main\java\sageassistant\service\StockService.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sageassistant.dao.StockMapper;
import sageassistant.model.StockHistory;
import sageassistant.model.StockSummary;

@Service
public class StockService {

    // private static final Logger log = LogManager.getLogger();

    @Autowired
    private StockMapper stockMapper;

    public String checkPN(@Param("pnRoot") String pnRoot) {
        return stockMapper.checkPN(pnRoot);
    }

    public String getStockOptionPN(@Param("pnRoot") String pnRoot) {
        // remove special character, pnRoot must be url encoded,
        pnRoot = pnRoot.replaceAll(",|\\.| |-|_|/|\\\\", "");
        return stockMapper.findStockOptionPN(pnRoot);
    }

    public String getStockQty(@Param("Site") String Site, @Param("PnRoot") String pnRoot) {
        if (stockMapper.findStockQty(Site, pnRoot) == null) {
            return "0";
        } else {
            return stockMapper.findStockQty(Site, pnRoot);
        }
    }

    public List<StockSummary> getStockSummary(@Param("Site") String Site) {
        return stockMapper.findStockSummaryBySite(Site);
    }

    public List<StockHistory> getStockHistory(
        @Param("Site") String Site,
        @Param("PnOrName") String PnOrName,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    ) {
        return stockMapper.findStockHistoryBySite(Site, PnOrName, DateFrom, DateTo);
    }
}
