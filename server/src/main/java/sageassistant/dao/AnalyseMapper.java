/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:10:21
 * @FilePath       : \server\src\main\java\sageassistant\dao\AnalyseMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.AnalysePurchase;
import sageassistant.model.AnalyseQuote;
import sageassistant.model.AnalyseQuoteSalesCost;
import sageassistant.model.AnalyseSales;

@Mapper
public interface AnalyseMapper {
    List<AnalyseQuoteSalesCost> analyseQuoteSalesCost(
        @Param("Site") String Site,
        @Param("CategoryCode") String CategoryCode,
        @Param("PnRoot") String PnRoot,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo,
        @Param("Limit") Integer Limit
    );

    List<AnalysePurchase> analysePurchase(
        @Param("Site") String Site,
        @Param("PnRoot") String PnRoot,
        @Param("Currency") String Currency,
        @Param("LastN") String LastN
    );

    List<AnalyseQuote> analyseQuote(
        @Param("Site") String Site,
        @Param("PnRoot") String PnRoot,
        @Param("Currency") String Currency,
        @Param("LastN") String LastN
    );

    List<AnalyseSales> analyseSales(
        @Param("Site") String Site,
        @Param("PnRoot") String PnRoot,
        @Param("Currency") String Currency,
        @Param("LastN") String LastN
    );
}
