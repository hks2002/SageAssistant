package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.PnService;
import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class PnController {

	@Autowired
	private PnService pnService;

	@GetMapping("/Data/PNHelper")
	public String getPNs(@RequestParam(value = "PN", required = false, defaultValue = "%%") String pnOrPnRoot,
			@RequestParam(value = "Count", required = false, defaultValue = "200") Integer count) {
		return Utils.listToString(pnService.findPnByStartWith(pnOrPnRoot, count));
	}

	@GetMapping("/Data/MakeShortPn")
	public String makeShortPn(@RequestParam(value = "Pn", required = true) String pn) {
		return Utils.makeShortPn(pn);
	}

	@GetMapping("/Data/PNsInFamily")
	public String getPNsInFamily(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return Utils.listToString(pnService.findAllPnByPnRoot(pnRoot));
	}

	@GetMapping("/Data/SalesHistory")
	public String getSalesHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return Utils.listToString(pnService.findSalesHistoryByPnRoot(pnRoot, 0));
	}

	@GetMapping("/Data/QuoteHistory")
	public String getQuoteHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return Utils.listToString(pnService.findQuoteHistoryByPnRoot(pnRoot, 0));
	}

	@GetMapping("/Data/CostHistory")
	public String getCostHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return Utils.listToString(pnService.findCostHistoryByPnRoot(pnRoot, 0));
	}

	@GetMapping("/Data/DeliveryDuration")
	public String getDeliveryDuration(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return Utils.listToString(pnService.findDeliveryDurationByPnRoot(pnRoot));
	}

	@GetMapping("/Data/InventoryStock")
	public String getInventoryStock(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return Utils.listToString(pnService.findStockInfoByPnRoot(pnRoot));
	}

}
