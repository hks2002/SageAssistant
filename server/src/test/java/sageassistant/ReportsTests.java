package sageassistant;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class ReportsTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	JdbcTemplate jdbcTemplate;

	@MockBean
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private String[] reports = { "COC", "Delivery", "PurchaseOrder", "Delivery" };

	@Test
	public void testCOCshowPdf() throws Exception {
		for (int i = 0; i < reports.length; ++i) {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/Report/" + reports[i] + "/showPdf"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
						.andDo(print());
		}

	}

	@Test
	public void testCOCexportPdf() throws Exception {
		for (int i = 0; i < reports.length; ++i) {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/Report/" + reports[i] + "/exportPdf"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
						.andDo(print());
		}
	}

	@Test
	public void testCOCexportWord() throws Exception {
		for (int i = 0; i < reports.length; ++i) {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/Report/" + reports[i] + "/exportWord"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().contentType("text/rtf"))
						.andDo(print());
		}
	}

}
