package sageassistant.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.SupplyService;
import sageassistant.utils.Utils;

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
		return Utils.listToString(supplyService.supplyByCodeOrName(SupplyCodeOrName, count));
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
		return Utils.listToString(supplyService.supplyTotalAmount(SupplyCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplyTotalProjectQty")
	public String getSupplyTotalProjectQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(supplyService.supplyTotalProjectQty(SupplyCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplyTotalItemQty")
	public String getSupplyTotalItemQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(supplyService.supplyTotalItemQty(SupplyCode, DateFrom, DateTo));
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
		return Utils.listToString(supplyService.supplyOpenAmount(SupplyCode));
	}

	@GetMapping("/Data/SupplyOpenProjectQty")
	public String getSupplyOpenProjectQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return Utils.listToString(supplyService.supplyOpenProjectQty(SupplyCode));
	}

	@GetMapping("/Data/SupplyOpenItemQty")
	public String getSupplyOpenItemQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return Utils.listToString(supplyService.supplyOpenItemQty(SupplyCode));
	}

	@GetMapping("/Data/SupplyOpenQty")
	public String getSupplyOpenQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return Utils.listToString(supplyService.supplyOpenQty(SupplyCode));
	}
	
	@GetMapping("/Data/SupplyOpenProductQty")
	public String getSupplyOpenProductQty(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return Utils.listToString(supplyService.supplyOpenProductQty(SupplyCode));
	}

	@GetMapping("/Data/SupplyDeliveryHistory")
	public String getSupplyDeliveryHistory(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(supplyService.supplyDeliveryHistory(SupplyCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplyDelayHistory")
	public String getSupplyDelayHistory(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(supplyService.supplyDelayHistory(SupplyCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplyOpenItems")
	public String getSupplyOpenItems(
			@RequestParam(value = "SupplyCode", required = false, defaultValue = "NULL") String SupplyCode) {
		return Utils.listToString(supplyService.supplyOpenItems(SupplyCode));
	}
}
