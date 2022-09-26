/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:13:06
 * @FilePath       : \server\src\main\java\sageassistant\dao\FinancialMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.FinancialBalance;
import sageassistant.model.FinancialInvoicePay;
import sageassistant.model.FinancialInvoicePayPro;

@Mapper
public interface FinancialMapper {
    List<FinancialBalance> findAccoutBalanceForAll(@Param("Site") String Site, @Param("Year") String Year);

    // AcountNO AcountNOList only one
    List<FinancialBalance> findAccoutBalanceByAccountNO(
        @Param("Site") String Site,
        @Param("Year") String Year,
        @Param("AccountNOs") String[] AccountNOs
    );

    List<FinancialInvoicePay> findInvoicePay(
        @Param("Site") String Site,
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );

    List<FinancialInvoicePayPro> findInvoicePayPro(
        @Param("Site") String Site,
        @Param("CustomerCode") String CustomerCode,
        @Param("DateFrom") String DateFrom,
        @Param("DateTo") String DateTo
    );
}
