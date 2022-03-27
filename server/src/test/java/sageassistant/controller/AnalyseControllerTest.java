package sageassistant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AnalyseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAnalyseQuoteSalesCost() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/AnalysisQuoteSalesCost?Site=ZHU&CategoryCode=CXXX&DateFrom=2022-01-01&DateTo=2022-01-31"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/AnalysisQuoteSalesCost?Site=ALL&CategoryCode=CXXX&DateFrom=2022-01-01&DateTo=2022-01-31"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
