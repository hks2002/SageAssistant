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
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetInvoiceBody() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/InvoiceBodyByInvoiceNO"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetInvoiceBodyByFaPiao() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/InvoiceBodyByFaPiao"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetInvoiceHeader() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/InvoiceBodyByInvoiceNO"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetInvoiceHeaderByFaPiao() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/InvoiceHeaderByFaPiao"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetInvoiceNO() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/InvoiceNO"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
