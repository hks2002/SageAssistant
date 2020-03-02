package sageassistant;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ProductsController extends NamedParameterJdbcTemplateController {
	@GetMapping("/Data/PNHelper")
	public String getPNs(@RequestParam(value = "PnOrPnRoot", required = false, defaultValue = "%%") String pnOrPnRoot) {

		MapSqlParameterSource sqlParas = new MapSqlParameterSource();
		sqlParas.addValue("pnOrPnRoot", pnOrPnRoot + "%");

		return this.getFromDB("PNHelper", "sql/PNHelper.sql", sqlParas);
	}

	@GetMapping("/Data/PNsInFamily")
	public String getPNsInFamily(@RequestParam(value = "PnRoot", required = false, defaultValue = "") String pnRoot) {

		MapSqlParameterSource sqlParas = new MapSqlParameterSource();
		sqlParas.addValue("pnRoot", pnRoot);

		return this.getFromDB("PNsInFamily", "sql/PNsInFamily.sql", sqlParas);
	}

	@GetMapping("/Data/SalesHistoryByPN")
	public String getSalesHistoryByPN(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "") String pnRoot) {

		MapSqlParameterSource sqlParas = new MapSqlParameterSource();
		sqlParas.addValue("pnRoot", pnRoot);
		
		return this.getFromDB("SalesHistoryByPN", "sql/SalesHistoryByPN.sql", sqlParas);
	}
	
	@GetMapping("/Data/QuoteHistoryByPN")
	public String getQuoteHistoryByPN(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "") String pnRoot) {

		MapSqlParameterSource sqlParas = new MapSqlParameterSource();
		sqlParas.addValue("pnRoot", pnRoot);

		return this.getFromDB("QuoteHistoryByPN", "sql/QuoteHistoryByPN.sql", sqlParas);
	}

	@GetMapping("/Data/DeliveryByPN")
	public String getDeliveryByPN(@RequestParam(value = "PnRoot", required = false, defaultValue = "") String pnRoot) {
		MapSqlParameterSource sqlParas = new MapSqlParameterSource();
		sqlParas.addValue("pnRoot", pnRoot);

		return this.getFromDB("DeliveryByPN", "sql/DeliveryByPN.sql", sqlParas);
	}
	
	@GetMapping("/Data/InventoryStockByPN")
	public String getInventoryStockByPN(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "") String pnRoot) {
		MapSqlParameterSource sqlParas = new MapSqlParameterSource();
		sqlParas.addValue("pnRoot", pnRoot);

		return this.getFromDB("InventoryStockByPN", "sql/InventoryStockByPN.sql", sqlParas);
	}
	
	@GetMapping("/Data/AttachmentPathByPN")
	public String getAttachmentPathByPN(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "") String pnRoot) {
		MapSqlParameterSource sqlParas = new MapSqlParameterSource();
		sqlParas.addValue("pnRoot", pnRoot);

		return this.getFromDB("AttachmentPathByPN", "sql/AttachmentPathByPN.sql", sqlParas);
	}
}
