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
public class SatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTobeClosedWO() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/TobeDelivery?Site=ZHU"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetTobeDealWithOrderLineBySite() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/TobeReceive?Site=ZHU"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetTobeDelivery() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/TobeDealWithOrderLine?Site=ZHU"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetTobePurchaseBom() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/TobePurchaseBom?Site=ZHU"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetTobeReceive() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/TobeClosedWO?Site=ZHU"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }
}
