/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-03-12 22:37:34                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-06 14:43:46                                                                      *
 * @FilePath              : src/test/java/sageassistant/data/dao/PnMapperTest.java                                   *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PnMapperTest {

    @Autowired
    PnMapper pnMapper;

    @Test
    void testFindAllPnByPnRoot() {
        pnMapper.findAllPnByPnRoot("956A1001G01");
    }

    @Test
    void testFindCostHistoryByPnRoot() {
        pnMapper.findCostHistoryByPnRoot("956A1001G01");
    }

    @Test
    void testFindDeliveryDurationByPnRoot() {
        pnMapper.findDeliveryDurationByPnRoot("956A1001G01");
    }

    @Test
    void testFindObsoletePnBySite() {
        pnMapper.findObsoletePnBySite("ZHU");
    }

    @Test
    void testFindOptionPn() {
        pnMapper.findOptionPn("956A1001G01");
    }

    @Test
    void testFindPnByLike() {
        pnMapper.findPnByLike("956A1001G01", 50);
    }

    @Test
    void testFindQuoteHistoryByPnRoot() {
        pnMapper.findQuoteHistoryByPnRoot("956A1001G01");
    }

    @Test
    void testFindSalesHistoryByPnRoot() {
        pnMapper.findSalesHistoryByPnRoot("956A1001G01");
    }
}
