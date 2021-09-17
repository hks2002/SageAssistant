package sageassistant.model;

import java.math.BigDecimal;

public class SupplierSummaryAmount extends AmodelTemplate {
	private static final long serialVersionUID = 1L;

	private String Site;
	private String SupplierCode;
	private String Currency;
	private BigDecimal Amount;
	private Float Rate;
	private BigDecimal USD;

	public String getSupplierCode() {
		return SupplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
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
