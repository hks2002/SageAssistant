package sageassistant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class SrvInfoControllerTests {
	@Value("${project.name}")
	private String name;

	@Value("${project.version}")
	private String version;

	@Value("${spring.boot.version}")
	private String springBootVersion;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	JdbcTemplate jdbcTemplate;

	@MockBean
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void testSrvVersion() throws Exception {
		MvcResult result =this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvVersion"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().string(version)).andReturn();
		
		String resp = result.getResponse().getContentAsString();
		System.out.println(resp);
	}
	
	@Test
	public void testSrvName() throws Exception {
		MvcResult result =this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvName"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().string(name)).andReturn();
		
		String resp = result.getResponse().getContentAsString();
		System.out.println(resp);
	}
	
	@Test
	public void testSrvInfo() throws Exception {
		MvcResult result =this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvInfo"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content()
													.string("{\"name\":\"" + name + "\",\"version\":\"" + version
															+ "\",\"springBootVersion\":\"" + springBootVersion
															+ "\"}")).andReturn();
		String resp = result.getResponse().getContentAsString();
		System.out.println(resp);
	}

}
