package sageassistant.controller;

import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.model.FinancialBalance;
import sageassistant.service.FinancialService;
import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class FinancialController {
	// private static final Logger log = LogManager.getLogger();

	@Autowired
	private FinancialService financialService;

	@GetMapping("/Data/FinancialBalance")
	public String getFinancialBalance(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		if (AccountNO.equals("")) {
				return financialService.getAccoutBalanceForAll(Site, Year).toString();
		} else {
				return financialService.getAccoutBalanceForAccountNO(Site, Year, AccountNO).toString();
		}
	}

	@GetMapping("/Data/FinancialBalanceM")
	public String getFinancialBalanceA(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceCDMB(Site, Year, AccountNO, "M");
	}

	@GetMapping("/Data/FinancialBalanceB")
	public String getFinancialBalanceB(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceCDMB(Site, Year, AccountNO, "B");
	}

	@GetMapping("/Data/FinancialBalanceC")
	public String getFinancialBalanceC(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceCDMB(Site, Year, AccountNO, "C");
	}

	@GetMapping("/Data/FinancialBalanceD")
	public String getFinancialBalanceD(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceCDMB(Site, Year, AccountNO, "D");
	}

	private String getFinancialBalanceCDMB(String Site, String Year, String AccountNO, String Cat) {
		if (Site.equals("") || Year.equals("")) {
			return "Must set Site and Year";
		}
		if (AccountNO.equals("")) {
			return "Must set AccountNO, if more than one AccountNO, use ',' between AccountNOs";
		}
		return Utils.listToString(financialService.getAccoutBalanceForAccountNOByCat(Site, Year, Cat,
		AccountNO));
	}

	@GetMapping("/Data/FinancialInvoicePay")
	public String getFinancialInvoicePay(
			@RequestParam(value = "Site", required = true, defaultValue = "ZHU") String Site,
			@RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo) {
		return Utils.listToString(financialService.getInvoicePay(Site, CustomerCode, DateFrom, DateTo));
	}

	@GetMapping("/Data/FinancialInvoicePayPro")
	public String getFinancialInvoicePayPro(
			@RequestParam(value = "Site", required = true, defaultValue = "ZHU") String Site,
			@RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo) {
		return Utils.listToString(financialService.getInvoicePayPro(Site, CustomerCode, DateFrom, DateTo));
	}
}
