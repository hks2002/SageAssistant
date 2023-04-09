/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-03-12 21:46:07                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 22:00:04                                                                      *
 * @FilePath              : src/test/java/com/da/sage/assistant/dao/CustomerMapperTest.java                          *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void testFindCustomerByCodeOrName() {
        customerMapper.findCustomerByCodeOrName("00870", 5);
    }

    @Test
    void testFindCustomerDelayHistory() {
        customerMapper.findCustomerDelayHistory("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindCustomerDeliveryHistory() {
        customerMapper.findCustomerDeliveryHistory("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindCustomerDetailsByCode() {
        customerMapper.findCustomerDetailsByCode("00870");
    }

    @Test
    void testFindCustomerOpenAmount() {
        customerMapper.findCustomerOpenAmount("00870");
    }

    @Test
    void testFindCustomerOpenItemQty() {
        customerMapper.findCustomerOpenItemQty("00870");
    }

    @Test
    void testFindCustomerOpenItems() {
        customerMapper.findCustomerOpenItems("00870");
    }

    @Test
    void testFindCustomerOpenProductQty() {
        customerMapper.findCustomerOpenProductQty("00870");
    }

    @Test
    void testFindCustomerOpenProjectQty() {
        customerMapper.findCustomerOpenProjectQty("00870");
    }

    @Test
    void testFindCustomerTotalAmount() {
        customerMapper.findCustomerTotalAmount("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindCustomerTotalItemQty() {
        customerMapper.findCustomerTotalItemQty("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindCustomerTotalProductQty() {
        customerMapper.findCustomerTotalProductQty("00870", "2020-01-01", "2020-12-31");
    }

    @Test
    void testFindCustomerTotalProjectQty() {
        customerMapper.findCustomerTotalProjectQty("00870", "2020-01-01", "2020-12-31");
    }
}
