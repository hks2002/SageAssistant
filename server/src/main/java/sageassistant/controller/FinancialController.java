package sageassistant.controller;

import java.util.ArrayList;
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
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO,
			@RequestParam(value = "AccountNOList", required = false, defaultValue = "") String AccountNOList) {

		if (AccountNO.equals("") && AccountNOList.equals("")) {
			if (Month.equals("")) {
				return financialService.getAccoutBalanceForAll(Site, Year).toString();
			} else {
				return financialService.getAccoutMonthBalanceForAll(Site, Year, Month).toString();
			}
		} else if (!AccountNOList.equals("")) { // AccountNOList first
			if (Month.equals("")) {
				return financialService.getAccoutBalanceForAccountNO(Site, Year, "", AccountNOList).toString();
			} else {
				return financialService.getAccoutMonthBalanceForAccountNO(Site, Year, Month, "", AccountNOList).toString();
			}
		} else { // Now it should be AccountNO
			if (Month.equals("")) {
				return financialService.getAccoutBalanceForAccountNO(Site, Year, AccountNO, null).toString();
			} else {
				return financialService.getAccoutMonthBalanceForAccountNO(Site, Year, Month, AccountNO, null).toString();
			}
		}
	}

	@GetMapping("/Data/FinancialBalanceB")
	public String getFinancialBalanceB(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO,
			@RequestParam(value = "AccountNOList", required = false, defaultValue = "") String AccountNOList) {

		return getFinancialBalanceBCD(Site, Year, Month, AccountNO, AccountNOList, "B");
	}
	
	@GetMapping("/Data/FinancialBalanceC")
	public String getFinancialBalanceC(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO,
			@RequestParam(value = "AccountNOList", required = false, defaultValue = "") String AccountNOList) {

		return getFinancialBalanceBCD(Site, Year, Month, AccountNO, AccountNOList, "C");
	}

	@GetMapping("/Data/FinancialBalanceD")
	public String getFinancialBalanceD(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "Year", required = false, defaultValue = "") String Year,
			@RequestParam(value = "Month", required = false, defaultValue = "") String Month,
			@RequestParam(value = "AccountNO", required = false, defaultValue = "") String AccountNO,
			@RequestParam(value = "AccountNOList", required = false, defaultValue = "") String AccountNOList) {

		return getFinancialBalanceBCD(Site, Year, Month, AccountNO, AccountNOList, "D");
	}
	

	private String getFinancialBalanceBCD(String Site,String Year,String Month,String AccountNO,String AccountNOList,String Cat) {

		if (Site.equals("") || Year.equals("") || Month.equals("")) {
			return "Must set Year and Month";
		}
		if (AccountNO.equals("") && AccountNOList.equals("")) {
			return "Must set AccountNO or AccountNOList";
		}
		
		List<FinancialMonthBalance> listMonth = new ArrayList<>();
		
		if (!AccountNOList.equals("")) { // AccountNOList first
			listMonth = financialService.getAccoutMonthBalanceForAccountNO(Site, Year, Month, "", AccountNOList);
		} else { // Now it should be AccountNO
			listMonth = financialService.getAccoutMonthBalanceForAccountNO(Site, Year, Month, AccountNO, null);
		}
		
		if (listMonth.size()==0) {
			return "No result";
		} else {
			switch (Cat) {
			case "B": 
				return listMonth.get(0).getB().toString();
			case "C":
				return listMonth.get(0).getC().toString();
			case "D":
				return listMonth.get(0).getD().toString();
			default:
				return listMonth.get(0).getB().toString();
			}
		}
	}
	
}
