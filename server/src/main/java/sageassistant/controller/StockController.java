package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.StockService;

@CrossOrigin
@RestController
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/Data/CheckPN")
	public String checkPN(
			@RequestParam(value = "PN", required = false, defaultValue = "--") String PN) {
		return stockService.checkPN(PN);
	}

	@GetMapping("/Data/StockOptionPN")
	public String getStockOptionPN(
			@RequestParam(value = "PN", required = false, defaultValue = "--") String PN) {
		return stockService.getStockOptionPN(PN);
	}

	@GetMapping("/Data/StockQty")
	public String getStockQty(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "PN", required = false, defaultValue = "--") String PN) {
		return stockService.getStockQty(Site, PN).toString();
	}

	@GetMapping("/Data/StockSummary")
	public String getStockSummary(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return stockService.getStockSummary(Site).toString();
	}

	@GetMapping("/Data/StockHistory")
	public String getStockHistory(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "PnOrName", required = false, defaultValue = "%%") String PnOrName,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2022-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo) {
		return stockService.getStockHistory(Site, PnOrName, DateFrom, DateTo).toString();
	}

}
