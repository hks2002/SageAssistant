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
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCustomerDelayHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerDelayHistory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerDeliveryHistory() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerDeliveryHistory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerDetails"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerHelper"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerOpenAmount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerOpenAmount"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerOpenItemQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerOpenItemQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerOpenItems() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerOpenItems"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerOpenProductQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerOpenProductQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerOpenProjectQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerOpenProjectQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerOpenQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerOpenQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerTotalAmount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerTotalAmount"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerTotalItemQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerTotalItemQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerTotalProductQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerTotalProductQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerTotalProjectQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerTotalProjectQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetCustomerTotalQty() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/CustomerTotalQty"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
