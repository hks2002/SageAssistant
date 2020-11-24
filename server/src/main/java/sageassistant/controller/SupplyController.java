package sageassistant.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.SupplyService;

@CrossOrigin
@RestController
public class SupplyController {
	//private static final Logger log = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private SupplyService supplyService;

	@GetMapping("/Data/SupplyName")
	public String getSupplyName(@RequestParam(value = "SupplyCodeOrName", required = false, defaultValue = "%%") String SupplyCodeOrName,
            @RequestParam(value = "Count", required = false, defaultValue = "50") Integer count) {
		return supplyService.supplyByCodeOrName(SupplyCodeOrName,count).toString();
	}
	
	@GetMapping("/Data/SupplyTotalAmount")
	public String getSupplyTotalAmount(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyTotalAmount(SupplyCode).toString();
	}

	@GetMapping("/Data/SupplyTotalProjectQty")
	public String getSupplyTotalProjectQty(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyTotalProjectQty(SupplyCode).toString();
	}
	
	@GetMapping("/Data/SupplyTotalLineQty")
	public String getSupplyTotalLineQty(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyTotalLineQty(SupplyCode).toString();
	}
	
	@GetMapping("/Data/SupplyTotalProductQty")
	public String getSupplyTotalProductQty(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyTotalProductQty(SupplyCode).toString();
	}
	@GetMapping("/Data/SupplyOpenAmount")
	public String getSupplyOpenAmount(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenAmount(SupplyCode).toString();
	}

	@GetMapping("/Data/SupplyOpenProjectQty")
	public String getSupplyOpenProjectQty(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenProjectQty(SupplyCode).toString();
	}
	
	@GetMapping("/Data/SupplyOpenLineQty")
	public String getSupplyOpenLineQty(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenLineQty(SupplyCode).toString();
	}
	
	@GetMapping("/Data/SupplyOpenProductQty")
	public String getSupplyOpenProductQty(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenProductQty(SupplyCode).toString();
	}
	
	@GetMapping("/Data/SupplyDeliveryHistory")
	public String getSupplyDeliveryHistory(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
            @RequestParam(value = "YearCount", required = false, defaultValue = "3") int YearCount) {
		return supplyService.supplyDeliveryHistory(SupplyCode, YearCount).toString();
	}
	
	@GetMapping("/Data/SupplyDelayHistory")
	public String getSupplyDelayHistory(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
            @RequestParam(value = "YearCount", required = false, defaultValue = "3") int YearCount) {
		return supplyService.supplyDelayHistory(SupplyCode, YearCount).toString();
	}
	
	@GetMapping("/Data/SupplyOpenLines")
	public String getSupplyOpenLines(@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenLines(SupplyCode).toString();
	}
}
