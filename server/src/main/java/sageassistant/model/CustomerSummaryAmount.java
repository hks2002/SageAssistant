package sageassistant.model;

import java.math.BigDecimal;

import sageassistant.model.base.ModelTemplate;

public class CustomerSummaryAmount extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String Site;
	private String CustomerCode;
	private String Currency;
	private BigDecimal Amount;
	private Float Rate;
	private BigDecimal USD;

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setSupplierCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public BigDecimal getAmount() {
		return Amount;
	}

	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}

	public Float getRate() {
		return Rate;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}

}
