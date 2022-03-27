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
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPurchaseDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/PurchaseDate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierDelayHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierDelayHistory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierDeliveryHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierDeliveryHistory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierDetails"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierHelper"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierOpenAmount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierOpenAmount"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierOpenItemQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierOpenItemQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierOpenItems() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierOpenItems"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierOpenProductQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierOpenProductQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierOpenProjectQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierOpenProjectQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierOpenQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierOpenQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierTotalAmount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierTotalAmount"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierTotalItemQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierTotalItemQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierTotalProductQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierTotalProductQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierTotalProjectQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierTotalProjectQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetSupplierTotalQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SupplierTotalQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
