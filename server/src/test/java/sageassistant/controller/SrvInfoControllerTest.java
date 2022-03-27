package sageassistant.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SrvInfoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// @MockBean
	// JdbcTemplate jdbcTemplate;

	// @MockBean
	// private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void testSrvVersion() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvVersion"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.matchesRegex("\\d+\\.\\d+\\.\\d+")))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testSrvName() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvName"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("SageAssistantSrv"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testSrvInfo() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvInfo"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("SageAssistantSrv"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.version", Matchers.matchesRegex("\\d+\\.\\d+\\.\\d+")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.springBootVersion",
						Matchers.matchesRegex("\\d+\\.\\d+\\.\\d+\\.RELEASE")))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testSrvProjectDependencies() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvProjectDependencies"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(29))
				.andDo(MockMvcResultHandlers.print());
	}
}
