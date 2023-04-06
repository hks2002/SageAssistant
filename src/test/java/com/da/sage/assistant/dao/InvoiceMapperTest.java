/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-03-12 23:01:22                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-06 14:43:40                                                                      *
 * @FilePath              : src/test/java/sageassistant/data/dao/InvoiceMapperTest.java                              *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvoiceMapperTest {

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Test
    void testFindInvoiceBodyByFaPiao() {
        invoiceMapper.findInvoiceBodyByFaPiao("0000000000");
    }

    @Test
    void testFindInvoiceBodyByInvoiceNO() {
        invoiceMapper.findInvoiceBodyByInvoiceNO("ZFC1901001");
    }

    @Test
    void testFindInvoiceHeaderByFaPiao() {
        invoiceMapper.findInvoiceHeaderByFaPiao("0000000000");
    }

    @Test
    void testFindInvoiceHeaderByInvoiceNO() {
        invoiceMapper.findInvoiceHeaderByInvoiceNO("ZFC1901001");
    }

    @Test
    void testFindInvoiceNOByInvoiceNO() {
        invoiceMapper.findInvoiceNOByInvoiceNO("ZFC1901001", 5);
    }
}
