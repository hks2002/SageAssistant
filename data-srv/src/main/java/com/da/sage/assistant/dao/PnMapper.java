/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:55:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 19:31:39                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/dao/PnMapper.java                                    *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.da.sage.assistant.model.CostHistory;
import com.da.sage.assistant.model.DeliveryDuration;
import com.da.sage.assistant.model.PnDetails;
import com.da.sage.assistant.model.PnRootPn;
import com.da.sage.assistant.model.PnStatus;
import com.da.sage.assistant.model.QuoteHistory;
import com.da.sage.assistant.model.SalesHistory;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PnMapper {
    List<PnRootPn> findPnByLike(@Param("PnOrPnRoot") String PnRootOrPn, @Param("Count") Integer Count);

    List<String> findOptionPn(@Param("PnRoot") String PnRoot);

    List<PnDetails> findAllPnByPnRoot(@Param("PnRoot") String PnRoot);

    List<PnStatus> findObsoletePnBySite(@Param("Site") String Site);

    List<SalesHistory> findSalesHistoryByPnRoot(@Param("PnRoot") String PnRoot);

    List<QuoteHistory> findQuoteHistoryByPnRoot(@Param("PnRoot") String PnRoot);

    List<CostHistory> findCostHistoryByPnRoot(@Param("PnRoot") String PnRoot);

    List<DeliveryDuration> findDeliveryDurationByPnRoot(@Param("PnRoot") String PnRoot);
}
