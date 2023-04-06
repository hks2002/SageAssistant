/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-03-12 19:32:19                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:59:05                                                                      *
 * @FilePath              : src/test/java/com/da/sage/assistant/dao/SupplierMapperTest.java                          *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SupplierMapperTest {

    @Autowired
    SupplierMapper supplierMapper;

    @Test
    void testFindPurchaseDate() {
        supplierMapper.findPurchaseDate("ZCF2301001");
    }

    @Test
    void testFindSupplierByCodeOrName() {
        supplierMapper.findSupplierByCodeOrName("00870", 5);
    }

    @Test
    void testFindSupplierDelayHistory() {
        supplierMapper.findSupplierDelayHistory("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindSupplierDeliveryHistory() {
        supplierMapper.findSupplierDeliveryHistory("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindSupplierDetailsByCode() {
        supplierMapper.findSupplierDetailsByCode("00870");
    }

    @Test
    void testFindSupplierOpenAmount() {
        supplierMapper.findSupplierOpenAmount("00870");
    }

    @Test
    void testFindSupplierOpenItemQty() {
        supplierMapper.findSupplierOpenItemQty("00870");
    }

    @Test
    void testFindSupplierOpenItems() {
        supplierMapper.findSupplierOpenItems("00870");
    }

    @Test
    void testFindSupplierOpenProductQty() {
        supplierMapper.findSupplierOpenProductQty("00870");
    }

    @Test
    void testFindSupplierOpenProjectQty() {
        supplierMapper.findSupplierOpenProjectQty("00870");
    }

    @Test
    void testFindSupplierTotalAmount() {
        supplierMapper.findSupplierTotalAmount("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindSupplierTotalItemQty() {
        supplierMapper.findSupplierTotalItemQty("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindSupplierTotalProductQty() {
        supplierMapper.findSupplierTotalProductQty("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindSupplierTotalProjectQty() {
        supplierMapper.findSupplierTotalProjectQty("00870", "2020-01-01", "2020-12-31");
    }
}
