/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:35:30
 * @FilePath       : \server\src\main\java\sageassistant\dao\PnMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.CostHistory;
import sageassistant.model.DeliveryDuration;
import sageassistant.model.PnDetails;
import sageassistant.model.PnRootPn;
import sageassistant.model.PnStatus;
import sageassistant.model.QuoteHistory;
import sageassistant.model.SalesHistory;

@Mapper
public interface PnMapper {
    List<PnRootPn> findPnByLike(@Param("PnOrPnRoot") String PnRootOrPn, @Param("Count") Integer Count);

    List<String> findOptionPn(@Param("PnRoot") String PnRoot);

    List<PnDetails> findAllPnByPnRoot(@Param("PnRoot") String PnRoot);

    List<PnStatus> findObseletPnBySite(@Param("Site") String Site);

    List<SalesHistory> findSalesHistoryByPnRoot(@Param("PnRoot") String PnRoot);

    List<QuoteHistory> findQuoteHistoryByPnRoot(@Param("PnRoot") String PnRoot);

    List<CostHistory> findCostHistoryByPnRoot(@Param("PnRoot") String PnRoot);

    List<DeliveryDuration> findDeliveryDurationByPnRoot(@Param("PnRoot") String PnRoot);
}
