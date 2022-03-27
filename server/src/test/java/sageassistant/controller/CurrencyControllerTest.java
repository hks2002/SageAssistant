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
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCurrencyRate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CurrencyRate"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CurrencyRate?Date=2021-12-31&Sour=RMB"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CurrencyRate?Date=2021-12-31&Sour=RMB&Dist=USD"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCurrencyRateBatch() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CurrencyRateBatch"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CurrencyRateBatch?query=RMBUSD2010-10-10_RMBUSD2010-10-11_RMBUSD2010-10-12"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());        
    }
}
