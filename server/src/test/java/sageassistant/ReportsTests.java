package sageassistant;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest
public class ReportsTests {
	@Autowired
	private MockMvc mockMvc;


	private String[] reports = { "COC", "Delivery", "PurchaseOrder", "WorkOrder" };

	@Test
	public void testShowPdf() throws Exception {
		for (int i = 0; i < reports.length; ++i) {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/Report/" + reports[i] + "/showPdf"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
						.andDo(print());
		}

	}

	@Test
	public void testExportPdf() throws Exception {
		for (int i = 0; i < reports.length; ++i) {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/Report/" + reports[i] + "/exportPdf"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
						.andDo(print());
		}
	}

	@Test
	public void testExportWord() throws Exception {
		for (int i = 0; i < reports.length; ++i) {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/Report/" + reports[i] + "/exportWord"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.content().contentType("text/rtf"))
						.andDo(print());
		}
	}

}
