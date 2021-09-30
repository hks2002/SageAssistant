package sageassistant.controller;

import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.model.FinancialMonthBalance;
import sageassistant.service.FinancialService;

@CrossOrigin
@RestController
public class FinancialController {
	// private static final Logger log = LogManager.getLogger();

	@Autowired
	private FinancialService financialService;

	@GetMapping("/Data/FinancialBalance")
	public String getFinancialBalance(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		if (AccountNO.equals("") ) {
			if (Month.equals("")) {
				return financialService.getAccoutBalanceForAll(Site, Year).toString();
			} else {
				return financialService.getAccoutMonthBalanceForAll(Site, Year, Month).toString();
			}
		}  else {
			if (Month.equals("")) {
				return financialService.getAccoutBalanceForAccountNO(Site, Year, AccountNO).toString();
			} else {
				return financialService.getAccoutMonthBalanceForAccountNO(Site, Year, Month, AccountNO).toString();
			}
		}
	}

	@GetMapping("/Data/FinancialBalanceA")
	public String getFinancialBalanceA(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceABCD(Site, Year, Month, AccountNO, "A");
	}
	
	@GetMapping("/Data/FinancialBalanceB")
	public String getFinancialBalanceB(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceABCD(Site, Year, Month, AccountNO, "B");
	}
	
	@GetMapping("/Data/FinancialBalanceC")
	public String getFinancialBalanceC(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceABCD(Site, Year, Month, AccountNO, "C");
	}

	@GetMapping("/Data/FinancialBalanceD")
	public String getFinancialBalanceD(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO) {

		return getFinancialBalanceABCD(Site, Year, Month, AccountNO, "D");
	}
	

	private String getFinancialBalanceABCD(String Site,String Year,String Month,String AccountNO, String Cat) {

		if (Site.equals("") || Year.equals("") || Month.equals("")) {
			return "Must set Year and Month";
		}
		if (AccountNO.equals("")) {
			return "Must set AccountNO, if more than one AccountNO, use , between AccountNOs";
		}
		
		List<FinancialMonthBalance> listMonth = financialService.getAccoutMonthBalanceForAccountNO(Site, Year, Month, AccountNO);
		
		if (listMonth.size()==0) {
			return "0";
		} else {
			switch (Cat) {
			case "A": 
				return String.format("%,.2f",listMonth.get(0).getA().doubleValue());
			case "B": 
				return String.format("%,.2f",listMonth.get(0).getB().doubleValue());
			case "C":
				return String.format("%,.2f",listMonth.get(0).getC().doubleValue());
			case "D":
				return String.format("%,.2f",listMonth.get(0).getD().doubleValue());
			default:
				return String.format("%,.2f",listMonth.get(0).getB().doubleValue());
			}
		}
	}
	
}
