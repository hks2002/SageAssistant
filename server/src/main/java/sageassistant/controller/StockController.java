package sageassistant.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.StockService;

@CrossOrigin
@RestController
public class StockController {
	// private static final Logger log =
	// LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private StockService stockService;

	@GetMapping("/Data/StockSummary")
	public String getStockSummary(
			@RequestParam(value = "site", required = false, defaultValue = "ZHU") String Site) {
		return stockService.getStockSummary(Site).toString();
	}

	@GetMapping("/Data/StockHistory")
	public String getStockHistory(
			@RequestParam(value = "site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "PnOrName", required = false, defaultValue = "%%") String PnOrName,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return stockService.getStockHistory(Site, PnOrName, DateFrom, DateTo).toString();
	}
}
