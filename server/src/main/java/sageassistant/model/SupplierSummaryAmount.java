package sageassistant.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplierSummaryAmount implements Serializable {
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

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
