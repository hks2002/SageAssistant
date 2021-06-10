package sageassistant.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.utils.Utils;
import sageassistant.service.SupplierService;

@CrossOrigin
@RestController
public class SupplierController {
	// private static final Logger log =
	// LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private SupplierService SupplierService;

	@GetMapping("/Data/SupplierHelper")
	public String getSupplierName(
			@RequestParam(value = "SupplierCodeOrName", required = false, defaultValue = "%%") String SupplierCodeOrName,
			@RequestParam(value = "Count", required = false, defaultValue = "50") Integer count) {
		return Utils.listToString(SupplierService.supplierByCodeOrName(SupplierCodeOrName, count));
	}

	@GetMapping("/Data/SupplierDetails")
	public String getSupplierDetails(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode) {
		return SupplierService.supplierDetails(SupplierCode).toString();
	}

	@GetMapping("/Data/SupplierTotalAmount")
	public String getSupplierTotalAmount(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(SupplierService.supplierTotalAmount(SupplierCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplierTotalProjectQty")
	public String getSupplierTotalProjectQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(SupplierService.supplierTotalProjectQty(SupplierCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplierTotalItemQty")
	public String getSupplierTotalItemQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(SupplierService.supplierTotalItemQty(SupplierCode, DateFrom, DateTo));
	}
	
	@GetMapping("/Data/SupplierTotalQty")
	public String getSupplierTotalQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return SupplierService.supplierTotalQty(SupplierCode, DateFrom, DateTo).toString();
	}
	
	@GetMapping("/Data/SupplierTotalProductQty")
	public String getSupplierTotalProductQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return SupplierService.supplierTotalProductQty(SupplierCode, DateFrom, DateTo).toString();
	}

	@GetMapping("/Data/SupplierOpenAmount")
	public String getSupplierOpenAmount(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode) {
		return Utils.listToString(SupplierService.supplierOpenAmount(SupplierCode));
	}

	@GetMapping("/Data/SupplierOpenProjectQty")
	public String getSupplierOpenProjectQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode) {
		return Utils.listToString(SupplierService.supplierOpenProjectQty(SupplierCode));
	}

	@GetMapping("/Data/SupplierOpenItemQty")
	public String getSupplierOpenItemQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode) {
		return Utils.listToString(SupplierService.supplierOpenItemQty(SupplierCode));
	}

	@GetMapping("/Data/SupplierOpenQty")
	public String getSupplierOpenQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode) {
		return Utils.listToString(SupplierService.supplierOpenQty(SupplierCode));
	}
	
	@GetMapping("/Data/SupplierOpenProductQty")
	public String getSupplierOpenProductQty(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode) {
		return Utils.listToString(SupplierService.supplierOpenProductQty(SupplierCode));
	}

	@GetMapping("/Data/SupplierDeliveryHistory")
	public String getSupplierDeliveryHistory(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(SupplierService.supplierDeliveryHistory(SupplierCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplierDelayHistory")
	public String getSupplierDelayHistory(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2099-12-31") String DateTo) {
		return Utils.listToString(SupplierService.supplierDelayHistory(SupplierCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/SupplierOpenItems")
	public String getSupplierOpenItems(
			@RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode) {
		return Utils.listToString(SupplierService.supplierOpenItems(SupplierCode));
	}
	
	@GetMapping("/Data/PurchaseDate")
	public String getPurchaseDate(
			@RequestParam(value = "PurchaseNO", required = true, defaultValue = "NULL") String PurchaseNO) {
		return SupplierService.purchaseDate(PurchaseNO);
	}

}
