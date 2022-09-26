/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:15:02
 * @FilePath       : \server\src\main\java\sageassistant\dao\StockMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.StockHistory;
import sageassistant.model.StockInfo;
import sageassistant.model.StockSummary;

@Mapper
public interface StockMapper {
    String checkPN(@Param("pnRoot") String pnRoot);

    String findStockOptionPN(@Param("pnRoot") String pnRoot);

    String findStockQty(@Param("Site") String Site, @Param("pnRoot") String pnRoot);

    List<StockInfo> findStockInfoByPnRoot(@Param("pnRoot") String pnRoot);

    List<StockSummary> findStockSummaryBySite(@Param("Site") String Site);

    List<StockHistory> findStockHistoryBySite(
        @Param("Site") String Site,
        @Param("PnOrName") String PnOrName,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );
}
