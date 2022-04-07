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

@Service
public class FinancialService {
	// private static final Logger log = LogManager.getLogger();

	@Autowired
	private FinancialMapper financialMapper;

	public List<FinancialBalance> getAccoutBalanceForAll(String Site, String Year) {
		return financialMapper.findAccoutBalanceForAll(Site, Year);
	}

	public List<FinancialBalance> getAccoutBalanceForAllByCat(String Site, String Year, String Cat) {
		List<FinancialBalance> list = financialMapper.findAccoutBalanceForAll(Site, Year);
		List<FinancialBalance> listBalance = new ArrayList<>();

		for (FinancialBalance o : list) {
			FinancialBalance oBalance = new FinancialBalance();
			oBalance.setAccountNO(o.getAccountNO());
			oBalance.setCurrency(o.getCurrency());
			oBalance.setYear(Year);

			switch (Cat) {
				case "C":
					oBalance.setC0(o.getC0());
					oBalance.setC1(o.getC1());
					oBalance.setC2(o.getC2());
					oBalance.setC3(o.getC3());
					oBalance.setC4(o.getC4());
					oBalance.setC5(o.getC5());
					oBalance.setC6(o.getC6());
					oBalance.setC7(o.getC7());
					oBalance.setC8(o.getC8());
					oBalance.setC9(o.getC9());
					oBalance.setC10(o.getC10());
					oBalance.setC11(o.getC11());
					oBalance.setC12(o.getC12());
					break;
				case "D":
					oBalance.setD0(o.getD0());
					oBalance.setD1(o.getD1());
					oBalance.setD2(o.getD2());
					oBalance.setD3(o.getD3());
					oBalance.setD4(o.getD4());
					oBalance.setD5(o.getD5());
					oBalance.setD6(o.getD6());
					oBalance.setD7(o.getD7());
					oBalance.setD8(o.getD8());
					oBalance.setD9(o.getD9());
					oBalance.setD10(o.getD10());
					oBalance.setD11(o.getD11());
					oBalance.setD12(o.getD12());
					break;
				case "M":
					oBalance.setM0(o.getM0());
					oBalance.setM1(o.getM1());
					oBalance.setM2(o.getM2());
					oBalance.setM3(o.getM3());
					oBalance.setM4(o.getM4());
					oBalance.setM5(o.getM5());
					oBalance.setM6(o.getM6());
					oBalance.setM7(o.getM7());
					oBalance.setM8(o.getM8());
					oBalance.setM9(o.getM9());
					oBalance.setM10(o.getM10());
					oBalance.setM11(o.getM11());
					oBalance.setM12(o.getM12());
					break;
				case "B":
					oBalance.setB0(o.getB0());
					oBalance.setB1(o.getB1());
					oBalance.setB2(o.getB2());
					oBalance.setB3(o.getB3());
					oBalance.setB4(o.getB4());
					oBalance.setB5(o.getB5());
					oBalance.setB6(o.getB6());
					oBalance.setB7(o.getB7());
					oBalance.setB8(o.getB8());
					oBalance.setB9(o.getB9());
					oBalance.setB10(o.getB10());
					oBalance.setB11(o.getB11());
					oBalance.setB12(o.getB12());
					break;
				default:
					oBalance.setB0(o.getB0());
					oBalance.setB1(o.getB1());
					oBalance.setB2(o.getB2());
					oBalance.setB3(o.getB3());
					oBalance.setB4(o.getB4());
					oBalance.setB5(o.getB5());
					oBalance.setB6(o.getB6());
					oBalance.setB7(o.getB7());
					oBalance.setB8(o.getB8());
					oBalance.setB9(o.getB9());
					oBalance.setB10(o.getB10());
					oBalance.setB11(o.getB11());
					oBalance.setB12(o.getB12());
			}
			listBalance.add(oBalance);
		}

		return listBalance;
	}

	public List<FinancialBalance> getAccoutBalanceForAccountNO(String Site, String Year, String AccountNO) {
		String[] AccountNOs = AccountNO.split(",");
		return financialMapper.findAccoutBalanceByAccountNO(Site, Year, AccountNOs);
	}

	public List<FinancialBalance> getAccoutBalanceForAccountNOByCat(String Site, String Year, String Cat,
			String AccountNO) {
		String[] AccountNOs = AccountNO.split(",");
		List<FinancialBalance> list = financialMapper.findAccoutBalanceByAccountNO(Site, Year, AccountNOs);
		List<FinancialBalance> listBalance = new ArrayList<>();

		for (FinancialBalance o : list) {
			FinancialBalance oBalance = new FinancialBalance();
			
			oBalance.setAccountNO(o.getAccountNO());
			oBalance.setCurrency(o.getCurrency());
			oBalance.setYear(Year);
			switch (Cat) {
				case "C":
					oBalance.setC0(o.getC0());
					oBalance.setC1(o.getC1());
					oBalance.setC2(o.getC2());
					oBalance.setC3(o.getC3());
					oBalance.setC4(o.getC4());
					oBalance.setC5(o.getC5());
					oBalance.setC6(o.getC6());
					oBalance.setC7(o.getC7());
					oBalance.setC8(o.getC8());
					oBalance.setC9(o.getC9());
					oBalance.setC10(o.getC10());
					oBalance.setC11(o.getC11());
					oBalance.setC12(o.getC12());
					break;
				case "D":
					oBalance.setD0(o.getD0());
					oBalance.setD1(o.getD1());
					oBalance.setD2(o.getD2());
					oBalance.setD3(o.getD3());
					oBalance.setD4(o.getD4());
					oBalance.setD5(o.getD5());
					oBalance.setD6(o.getD6());
					oBalance.setD7(o.getD7());
					oBalance.setD8(o.getD8());
					oBalance.setD9(o.getD9());
					oBalance.setD10(o.getD10());
					oBalance.setD11(o.getD11());
					oBalance.setD12(o.getD12());
					break;
				case "M":
					oBalance.setM0(o.getM0());
					oBalance.setM1(o.getM1());
					oBalance.setM2(o.getM2());
					oBalance.setM3(o.getM3());
					oBalance.setM4(o.getM4());
					oBalance.setM5(o.getM5());
					oBalance.setM6(o.getM6());
					oBalance.setM7(o.getM7());
					oBalance.setM8(o.getM8());
					oBalance.setM9(o.getM9());
					oBalance.setM10(o.getM10());
					oBalance.setM11(o.getM11());
					oBalance.setM12(o.getM12());
					break;
				case "B":
					oBalance.setB0(o.getB0());
					oBalance.setB1(o.getB1());
					oBalance.setB2(o.getB2());
					oBalance.setB3(o.getB3());
					oBalance.setB4(o.getB4());
					oBalance.setB5(o.getB5());
					oBalance.setB6(o.getB6());
					oBalance.setB7(o.getB7());
					oBalance.setB8(o.getB8());
					oBalance.setB9(o.getB9());
					oBalance.setB10(o.getB10());
					oBalance.setB11(o.getB11());
					oBalance.setB12(o.getB12());
					break;
				default:
					oBalance.setB0(o.getB0());
					oBalance.setB1(o.getB1());
					oBalance.setB2(o.getB2());
					oBalance.setB3(o.getB3());
					oBalance.setB4(o.getB4());
					oBalance.setB5(o.getB5());
					oBalance.setB6(o.getB6());
					oBalance.setB7(o.getB7());
					oBalance.setB8(o.getB8());
					oBalance.setB9(o.getB9());
					oBalance.setB10(o.getB10());
					oBalance.setB11(o.getB11());
					oBalance.setB12(o.getB12());
			}
			listBalance.add(oBalance);
		}

		return listBalance;
	}

	public List<FinancialInvoicePay> getInvoicePay(String Site, String CustomerCode, String DateFrom, String DateTo) {
		return financialMapper.findInvoicePay(Site, CustomerCode, DateFrom, DateTo);
	}

	public List<FinancialInvoicePayPro> getInvoicePayPro(String Site, String CustomerCode, String DateFrom,
			String DateTo) {
		return financialMapper.findInvoicePayPro(Site, CustomerCode, DateFrom, DateTo);
	}
}
