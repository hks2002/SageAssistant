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
public class FinancialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetFinancialBalance() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalance"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalance?Site=ZHU&Year=2022"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalance?Site=ZHU&Year=2022&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalance?Site=ZHU&Year=2022&&AccountNO=1002210"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceM() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceM"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Site and Year"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceM?Site=ZHU&Year=2022"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use ',' between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceM?Site=ZHU&Year=2022&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceM?Site=ZHU&Year=2022&AccountNO=1002210"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceB() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Site and Year"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB?Site=ZHU&Year=2022"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use ',' between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB?Site=ZHU&Year=2022&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB?Site=ZHU&Year=2022&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceC() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Site and Year"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC?Site=ZHU&Year=2022"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use ',' between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC?Site=ZHU&Year=2022&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC?Site=ZHU&Year=2022&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceD() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Site and Year"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD?Site=ZHU&Year=2022"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use ',' between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD?Site=ZHU&Year=2022&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD?Site=ZHU&Year=2022&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialInvoicePay() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialInvoicePay?Site=ZHU&DateFrom=2021-01-01&DateTo=2021-02-01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialInvoicePayPro() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialInvoicePayPro?Site=ZHU&DateFrom=2021-01-01&DateTo=2021-02-01"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andDo(MockMvcResultHandlers.print());
    }
}
