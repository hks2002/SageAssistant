/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:55:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 19:31:18                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/dao/FinancialMapper.java                             *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.da.sage.assistant.model.FinancialBalance;
import com.da.sage.assistant.model.FinancialInvoicePay;
import com.da.sage.assistant.model.FinancialInvoicePayPro;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FinancialMapper {
    List<FinancialBalance> findAccountBalanceForAll(@Param("Site") String Site, @Param("Year") String Year);

    // AccountNO AccountNOList only one
    List<FinancialBalance> findAccountBalanceByAccountNO(
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
