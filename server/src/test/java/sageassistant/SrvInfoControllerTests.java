package sageassistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
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
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// @Test
	public void testSrvInfo() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvVersion"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().string(version));
		// .andDo(MockMvcResultHandlers.print());
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvName"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().string(name));
		// .andDo(MockMvcResultHandlers.print());
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/SrvInfo"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content()
													.string("{\"name\":\"" + name + "\",\"version\":\"" + version
															+ "\",\"springBootVersion\":\"" + springBootVersion
															+ "\"}"));
		// .andDo(MockMvcResultHandlers.print());
	}

}
