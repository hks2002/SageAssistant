/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:55:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:50:53                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/dao/AnalysesMapper.java                              *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.da.sage.assistant.model.AnalysesPurchase;
import com.da.sage.assistant.model.AnalysesQuote;
import com.da.sage.assistant.model.AnalysesQuoteSalesCost;
import com.da.sage.assistant.model.AnalysesSales;

@Mapper
public interface AnalysesMapper {
    List<AnalysesQuoteSalesCost> analysesQuoteSalesCost(
        @Param("Site") String Site,
        @Param("CategoryCode") String CategoryCode,
        @Param("PnRoot") String PnRoot,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo,
        @Param("Limit") Integer Limit
    );

    List<AnalysesPurchase> analysesPurchase(
        @Param("Site") String Site,
        @Param("PnRoot") String PnRoot,
        @Param("Currency") String Currency,
        @Param("LastN") String LastN
    );

    List<AnalysesQuote> analysesQuote(
        @Param("Site") String Site,
        @Param("PnRoot") String PnRoot,
        @Param("Currency") String Currency,
        @Param("LastN") String LastN
    );

    List<AnalysesSales> analysesSales(
        @Param("Site") String Site,
        @Param("PnRoot") String PnRoot,
        @Param("Currency") String Currency,
        @Param("LastN") String LastN
    );
}
