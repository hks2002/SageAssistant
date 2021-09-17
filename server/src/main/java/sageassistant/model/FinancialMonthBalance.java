package sageassistant.model;

import java.math.BigDecimal;

public class FinancialMonthBalance extends AmodelTemplate {

	private static final long serialVersionUID = 1L;

	private String AccountNO;
	private String Currency;
	private String Year;
	private String Month;
	private BigDecimal B;
	private BigDecimal C;
	private BigDecimal D;

	public String getAccountNO() {
		return AccountNO;
	}

	public void setAccountNO(String accountNO) {
		AccountNO = accountNO;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public BigDecimal getB() {
		return B;
	}

	public void setB(BigDecimal b) {
		B = b;
	}

	public BigDecimal getC() {
		return C;
	}

	public void setC(BigDecimal c) {
		C = c;
	}

	public BigDecimal getD() {
		return D;
	}

	public void setD(BigDecimal d) {
		D = d;
	}

}
