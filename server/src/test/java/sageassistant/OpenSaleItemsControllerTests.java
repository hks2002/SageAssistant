package sageassistant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class OpenSaleItemsControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Test
	public void testOpenSaleItemsController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/Data/OpenSaleItems").param("facility", "ZHU"))
					.andExpect(MockMvcResultMatchers.status().isOk());
					//.andDo(print());
		
	}
}
