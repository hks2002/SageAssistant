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
public class CommonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetAllSites() throws Exception {
         this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/Sites"))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(Matchers.greaterThan(3)))
                 .andDo(MockMvcResultHandlers.print());
    }
}
