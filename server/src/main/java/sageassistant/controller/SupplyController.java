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
	// private static final Logger log =
	// LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private SupplyService supplyService;

	@GetMapping("/Data/SupplyHelper")
	public String getSupplyName(
			@RequestParam(value = "SupplyCodeOrName", required = false, defaultValue = "%%") String SupplyCodeOrName,
			@RequestParam(value = "Count", required = false, defaultValue = "50") Integer count) {
		return supplyService.supplyByCodeOrName(SupplyCodeOrName, count).toString();
	}

	@GetMapping("/Data/SupplyDetails")
	public String getSupplyDetails(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyDetails(SupplyCode).toString();
	}

	@GetMapping("/Data/SupplyTotalAmount")
	public String getSupplyTotalAmount(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return supplyService.supplyTotalAmount(SupplyCode, DateFrom, DateTo).toString();
	}

	@GetMapping("/Data/SupplyTotalProjectQty")
	public String getSupplyTotalProjectQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return supplyService.supplyTotalProjectQty(SupplyCode, DateFrom, DateTo).toString();
	}

	@GetMapping("/Data/SupplyTotalItemQty")
	public String getSupplyTotalItemQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return supplyService.supplyTotalItemQty(SupplyCode, DateFrom, DateTo).toString();
	}
	
	@GetMapping("/Data/SupplyTotalQty")
	public String getSupplyTotalQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return supplyService.supplyTotalQty(SupplyCode, DateFrom, DateTo).toString();
	}
	
	@GetMapping("/Data/SupplyTotalProductQty")
	public String getSupplyTotalProductQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return supplyService.supplyTotalProductQty(SupplyCode, DateFrom, DateTo).toString();
	}

	@GetMapping("/Data/SupplyOpenAmount")
	public String getSupplyOpenAmount(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenAmount(SupplyCode).toString();
	}

	@GetMapping("/Data/SupplyOpenProjectQty")
	public String getSupplyOpenProjectQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenProjectQty(SupplyCode).toString();
	}

	@GetMapping("/Data/SupplyOpenItemQty")
	public String getSupplyOpenItemQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenItemQty(SupplyCode).toString();
	}

	@GetMapping("/Data/SupplyOpenQty")
	public String getSupplyOpenQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenQty(SupplyCode).toString();
	}
	
	@GetMapping("/Data/SupplyOpenProductQty")
	public String getSupplyOpenProductQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenProductQty(SupplyCode).toString();
	}

	@GetMapping("/Data/SupplyDeliveryHistory")
	public String getSupplyDeliveryHistory(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return supplyService.supplyDeliveryHistory(SupplyCode, DateFrom, DateTo).toString();
	}

	@GetMapping("/Data/SupplyDelayHistory")
	public String getSupplyDelayHistory(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return supplyService.supplyDelayHistory(SupplyCode, DateFrom, DateTo).toString();
	}

	@GetMapping("/Data/SupplyOpenItems")
	public String getSupplyOpenItems(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return supplyService.supplyOpenItems(SupplyCode).toString();
	}
}
