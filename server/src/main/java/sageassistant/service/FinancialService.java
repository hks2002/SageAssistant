package sageassistant.service;

import java.util.ArrayList;
import java.util.List;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sageassistant.dao.FinancialMapper;
import sageassistant.model.FinancialBalance;
import sageassistant.model.FinancialInvoicePay;
import sageassistant.model.FinancialInvoicePayPro;
import sageassistant.model.FinancialMonthBalance;

@Service
public class FinancialService {
	 //private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private FinancialMapper financialMapper;
		
	public List<FinancialBalance> getAccoutBalanceForAll(String Site,String Year) {
		return financialMapper.findAccoutBalanceForAll(Site, Year);
	}
	
	public List<FinancialMonthBalance> getAccoutMonthBalanceForAll(String Site,String Year, String Month) {
		List<FinancialBalance> list = financialMapper.findAccoutBalanceForAll(Site, Year);
		List<FinancialMonthBalance> listMonth = new ArrayList<>();
		
		for (FinancialBalance o : list) {
			FinancialMonthBalance oMonth = new FinancialMonthBalance();
			oMonth.setAccountNO(o.getAccountNO());
			oMonth.setCurrency(o.getCurrency());
			oMonth.setYear(Year);
			oMonth.setMonth(Month);
			
			switch (Month) {
			case "0": 
				oMonth.setA(o.getA0());
				oMonth.setB(o.getB0());
				oMonth.setC(o.getC0());
				oMonth.setD(o.getD0());
				break;
			case "1": 
				oMonth.setA(o.getA1());
				oMonth.setB(o.getB1());
				oMonth.setC(o.getC1());
				oMonth.setD(o.getD1());
				break;
			case "2": 
				oMonth.setA(o.getA2());
				oMonth.setB(o.getB2());
				oMonth.setC(o.getC2());
				oMonth.setD(o.getD2());
				break;
			case "3": 
				oMonth.setA(o.getA3());
				oMonth.setB(o.getB3());
				oMonth.setC(o.getC3());
				oMonth.setD(o.getD3());
				break;
			case "4": 
				oMonth.setA(o.getA4());
				oMonth.setB(o.getB4());
				oMonth.setC(o.getC4());
				oMonth.setD(o.getD4());
				break;
			case "5": 
				oMonth.setA(o.getA5());
				oMonth.setB(o.getB5());
				oMonth.setC(o.getC5());
				oMonth.setD(o.getD5());
				break;
			case "6": 
				oMonth.setA(o.getA6());
				oMonth.setB(o.getB6());
				oMonth.setC(o.getC6());
				oMonth.setD(o.getD6());
				break;
			case "7": 
				oMonth.setA(o.getA7());
				oMonth.setB(o.getB7());
				oMonth.setC(o.getC7());
				oMonth.setD(o.getD7());
				break;
			case "8": 
				oMonth.setA(o.getA8());
				oMonth.setB(o.getB8());
				oMonth.setC(o.getC8());
				oMonth.setD(o.getD8());
				break;
			case "9": 
				oMonth.setA(o.getA9());
				oMonth.setB(o.getB9());
				oMonth.setC(o.getC9());
				oMonth.setD(o.getD9());
				break;
			case "10":
				oMonth.setA(o.getA10());
				oMonth.setB(o.getB10());
				oMonth.setC(o.getC10());
				oMonth.setD(o.getD10());
				break;
			case "11": 
				oMonth.setA(o.getA11());
				oMonth.setB(o.getB11());
				oMonth.setC(o.getC11());
				oMonth.setD(o.getD11());
				break;
			case "12":
				oMonth.setA(o.getA12());
				oMonth.setB(o.getB12());
				oMonth.setC(o.getC12());
				oMonth.setD(o.getD12());
				break;
			default:
				oMonth.setA(o.getA12());
				oMonth.setB(o.getB12());
				oMonth.setC(o.getC12());
				oMonth.setD(o.getD12());
			}
			listMonth.add(oMonth);
		}
		
		return listMonth;
	}
	
	
	public List<FinancialBalance> getAccoutBalanceForAccountNO(String Site, String Year, String AccountNO) {
		String[] AccountNOs = AccountNO.split(",");
		return financialMapper.findAccoutBalanceByAccountNO(Site, Year, AccountNOs);
	}
	
	public List<FinancialMonthBalance> getAccoutMonthBalanceForAccountNO(String Site,String Year,String Month, String AccountNO) {
		String[] AccountNOs = AccountNO.split(",");
		List<FinancialBalance> list = financialMapper.findAccoutBalanceByAccountNO(Site, Year, AccountNOs);
		List<FinancialMonthBalance> listMonth = new ArrayList<>();
		
		for (FinancialBalance o : list) {
			FinancialMonthBalance oMonth = new FinancialMonthBalance();
			oMonth.setAccountNO(o.getAccountNO());
			oMonth.setCurrency(o.getCurrency());
			oMonth.setYear(Year);
			oMonth.setMonth(Month);
			
			switch (Month) {
			case "0": 
				oMonth.setA(o.getA0());
				oMonth.setB(o.getB0());
				oMonth.setC(o.getC0());
				oMonth.setD(o.getD0());
				break;
			case "1": 
				oMonth.setA(o.getA1());
				oMonth.setB(o.getB1());
				oMonth.setC(o.getC1());
				oMonth.setD(o.getD1());
				break;
			case "2": 
				oMonth.setA(o.getA2());
				oMonth.setB(o.getB2());
				oMonth.setC(o.getC2());
				oMonth.setD(o.getD2());
				break;
			case "3": 
				oMonth.setA(o.getA3());
				oMonth.setB(o.getB3());
				oMonth.setC(o.getC3());
				oMonth.setD(o.getD3());
				break;
			case "4": 
				oMonth.setA(o.getA4());
				oMonth.setB(o.getB4());
				oMonth.setC(o.getC4());
				oMonth.setD(o.getD4());
				break;
			case "5": 
				oMonth.setA(o.getA5());
				oMonth.setB(o.getB5());
				oMonth.setC(o.getC5());
				oMonth.setD(o.getD5());
				break;
			case "6": 
				oMonth.setA(o.getA6());
				oMonth.setB(o.getB6());
				oMonth.setC(o.getC6());
				oMonth.setD(o.getD6());
				break;
			case "7": 
				oMonth.setA(o.getA7());
				oMonth.setB(o.getB7());
				oMonth.setC(o.getC7());
				oMonth.setD(o.getD7());
				break;
			case "8": 
				oMonth.setA(o.getA8());
				oMonth.setB(o.getB8());
				oMonth.setC(o.getC8());
				oMonth.setD(o.getD8());
				break;
			case "9": 
				oMonth.setA(o.getA9());
				oMonth.setB(o.getB9());
				oMonth.setC(o.getC9());
				oMonth.setD(o.getD9());
				break;
			case "10":
				oMonth.setA(o.getA10());
				oMonth.setB(o.getB10());
				oMonth.setC(o.getC10());
				oMonth.setD(o.getD10());
				break;
			case "11": 
				oMonth.setA(o.getA11());
				oMonth.setB(o.getB11());
				oMonth.setC(o.getC11());
				oMonth.setD(o.getD11());
				break;
			case "12":
				oMonth.setA(o.getA12());
				oMonth.setB(o.getB12());
				oMonth.setC(o.getC12());
				oMonth.setD(o.getD12());
				break;
			default:
				oMonth.setA(o.getA12());
				oMonth.setB(o.getB12());
				oMonth.setC(o.getC12());
				oMonth.setD(o.getD12());
			}
			listMonth.add(oMonth);
		}
		
		return listMonth;
	}
	
	public List<FinancialInvoicePay> getInvoicePay(String Site, String BPCode, String DateFrom, String DateTo) {
		return financialMapper.findInvoicePay(Site, BPCode, DateFrom, DateTo);
	}

	public List<FinancialInvoicePayPro> getInvoicePayPro(String Site, String BPCode, String DateFrom, String DateTo) {
		return financialMapper.findInvoicePayPro(Site, BPCode, DateFrom, DateTo);
	}
}