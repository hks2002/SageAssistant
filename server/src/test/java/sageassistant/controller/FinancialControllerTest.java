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
                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalance?Site=ZHU&Year=2022&Month=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalance?Site=ZHU&Year=2022&Month=1&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
                this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalance?Site=ZHU&Year=2022&Month=1&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceA() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceA"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Year and Month"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceA?Site=ZHU&Year=2022&Month=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use , between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceA?Site=ZHU&Year=2022&Month=1&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceA?Site=ZHU&Year=2022&Month=1&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceB() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Year and Month"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB?Site=ZHU&Year=2022&Month=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use , between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB?Site=ZHU&Year=2022&Month=1&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceB?Site=ZHU&Year=2022&Month=1&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceC() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Year and Month"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC?Site=ZHU&Year=2022&Month=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use , between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC?Site=ZHU&Year=2022&Month=1&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceC?Site=ZHU&Year=2022&Month=1&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetFinancialBalanceD() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set Year and Month"))
                .andDo(MockMvcResultHandlers.print());
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD?Site=ZHU&Year=2022&Month=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Must set AccountNO, if more than one AccountNO, use , between AccountNOs"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD?Site=ZHU&Year=2022&Month=1&AccountNO=xxxx"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"))
                .andDo(MockMvcResultHandlers.print());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/FinancialBalanceD?Site=ZHU&Year=2022&Month=1&AccountNO=2131000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
