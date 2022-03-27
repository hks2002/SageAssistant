package sageassistant.controller;

import org.hamcrest.Matchers;
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
public class PnControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testGetCostHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CostHistory"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
        .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CostHistory?PnRoot=956A1001G01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetDeliveryDuration() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/PNsInFamily"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
        .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/PNsInFamily?PnRoot=956A1001G01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(Matchers.greaterThan(1)))
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetInventoryStock() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/InventoryStock"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
        .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/InventoryStock?PnRoot=956A1001G01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetPNs() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/PNHelper"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(50))
        .andDo(MockMvcResultHandlers.print());

         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/PNHelper?PnOrPnRoot=956A"))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(Matchers.greaterThan(3)))
                 .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetPNsInFamily() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/PNsInFamily"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
        .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/PNsInFamily?PnRoot=956A1001G01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetQuoteHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/QuoteHistory"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
        .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/QuoteHistory?PnRoot=956A1001G01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSalesHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SalesHistory"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0))
        .andDo(MockMvcResultHandlers.print());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SalesHistory?PnRoot=956A1001G01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testMakeShortPn() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/MakeShortPn?Pn=956A1001G01_A"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("956A1001_A"))
        .andDo(MockMvcResultHandlers.print());
    }
}
