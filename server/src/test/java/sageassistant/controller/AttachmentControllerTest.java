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
public class AttachmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testGetAttachmentPath() throws Exception {
        // return an empty array []
        this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/AttachmentPath?Pn=956A1001_A"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void testHandleFileDelete() {

    }

    @Test
    void testHandleFileUpload() {

    }
}
