/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-03-12 19:34:33                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:57:27                                                                      *
 * @FilePath              : src/test/java/com/da/sage/assistant/dao/AnalysesMapperTest.java                          *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.dao;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnalysesMapperTest {

    @Autowired
    AnalysesMapper analysesMapper;

    @Test
    void testAnalysesQuoteSalesCost() {
        analysesMapper.analysesQuoteSalesCost("ZHU", "C0002", "956A1001G01", "2023-01-01", "2023-01-02", 1);
    }

    @Test
    void testAnalysesPurchase() {
        analysesMapper.analysesPurchase("ZHU", "956A1001G01", "RMB", "1");
    }

    @Test
    void testAnalysesQuote() {
        analysesMapper.analysesQuote("ZHU", "956A1001G01", "RMB", "1");
    }

    @Test
    void testAnalysesSales() {
        analysesMapper.analysesSales("ZHU", "956A1001G01", "RMB", "1");
    }
}
