package sageassistant;

import static org.junit.Assert.assertNotEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class ProductsControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	JdbcTemplate jdbcTemplate;

	// @Test
	public void testGetPNs() throws Exception {
		MvcResult result = this.mockMvc	.perform(MockMvcRequestBuilders.get("/Data/PNHelper"))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andReturn();
		assertNotEquals("[]", result.getResponse().getContentAsString());

	}

	// @Test
	public void testPNsInFamily() throws Exception {
		MvcResult result = this.mockMvc	.perform(MockMvcRequestBuilders.get("/Data/PNsInFamily"))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andReturn();
		assertNotEquals("[]", result.getResponse().getContentAsString());

	}

	// @Test
	public void testGetSalesHistoryByPN() throws Exception {
		MvcResult result = this.mockMvc	.perform(
												MockMvcRequestBuilders	.get("/Data/SalesHistoryByPN")
																		.param("PnRoot", "HU38122-3"))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andReturn();
		assertNotEquals("", result.getResponse().getContentAsString());

	}

	// @Test
	public void testGetQuoteHistoryByPN() throws Exception {
		MvcResult result = this.mockMvc	.perform(
												MockMvcRequestBuilders	.get("/Data/QuoteHistoryByPN")
																		.param("PnRoot", "HU38122-3"))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andReturn();
		assertNotEquals("", result.getResponse().getContentAsString());

	}

	// @Test
	public void testGetDeliveryByPN() throws Exception {
		MvcResult result = this.mockMvc	.perform(
												MockMvcRequestBuilders	.get("/Data/DeliveryByPN")
																		.param("PnRoot", "HU38122-3"))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andReturn();
		assertNotEquals("", result.getResponse().getContentAsString());

	}

	// @Test
	public void testGetInventoryStockByPN() throws Exception {
		MvcResult result = this.mockMvc	.perform(
												MockMvcRequestBuilders	.get("/Data/InventoryStockByPN")
																		.param("PnRoot", "HU38122-3"))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andReturn();
		assertNotEquals("", result.getResponse().getContentAsString());

	}

	// @Test
	public void testGetAttachmentPathByPN() throws Exception {
		MvcResult result = this.mockMvc	.perform(
												MockMvcRequestBuilders	.get("/Data/AttachmentPathByPN")
																		.param("PnRoot", "HU38122-3"))
										.andExpect(MockMvcResultMatchers.status().isOk())
										.andReturn();
		assertNotEquals("[]", result.getResponse().getContentAsString());

	}
}
