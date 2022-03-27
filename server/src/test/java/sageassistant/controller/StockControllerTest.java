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
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCheckPN() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CheckPN?PN=956A1001G01"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetStockOptionPN() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/StockOptionPN"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetStockQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/StockQty?PN=956A1001G01"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetStockHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/StockSummary?Site=--"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetStockSummary() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/StockHistory?Site=--"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }
}
