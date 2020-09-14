package sageassistant.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.PnService;

@CrossOrigin
@RestController
public class ProductsController {
	//private static final Logger log = LoggerFactory.getLogger(ProductsController.class);
	
	@Value("${attachment.path.linux}")
	private String attachmentPathLinux;

	@Value("${attachment.path.windows}")
	private String attachmentPathWindows;

	@Autowired
	private PnService pnService;

	
	@GetMapping("/Data/PNHelper")
	public String getPNs(@RequestParam(value = "PnOrPnRoot", required = false, defaultValue = "%%") String pnOrPnRoot,
            @RequestParam(value = "Count", required = false, defaultValue = "50") Integer count) {
		return pnService.findPnByStartWith(pnOrPnRoot,count).toString();
	}
		
	@GetMapping("/Data/PNsInFamily")
	public String getPNsInFamily(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findAllPnByPnRoot(pnRoot).toString();
	}

	@GetMapping("/Data/SalesHistory")
	public String getSalesHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findSalesHistoryByPnRoot(pnRoot, 0).toString();
	}

	@GetMapping("/Data/QuoteHistory")
	public String getQuoteHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findQuoteHistoryByPnRoot(pnRoot, 0).toString();
	}

	@GetMapping("/Data/CostHistory")
	public String getCostHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findCostHistoryByPnRoot(pnRoot, 0).toString();
	}
	
	@GetMapping("/Data/DeliveryDuration")
	public String getDeliveryDuration(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findDeliveryDurationByPnRoot(pnRoot).toString();
	}

	@GetMapping("/Data/InventoryStock")
	public String getInventoryStock(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findStockInfoByPnRoot(pnRoot).toString();
	}

}
