package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplySummaryAmount implements Serializable {
	private static final long serialVersionUID = 1L;

	private String Site;
	private String SupplyCode;
	private String SupplyName;
	private String Currency;
	private float Amount;
	private float Rate;
	private float USD;
	
	public String getSupplyCode() {
		return SupplyCode;
	}
	public void setSupplyCode(String supplyCode) {
		SupplyCode = supplyCode;
	}
	public String getSite() {
		return Site;
	}
	public void setSite(String site) {
		Site = site;
	}
	public String getSupplyName() {
		return SupplyName;
	}
	public void setSupplyName(String supplyName) {
		SupplyName = supplyName;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public float getAmount() {
		return Amount;
	}
	public void setAmount(float amount) {
		Amount = amount;
	}
	public float getRate() {
		return Rate;
	}
	public void setRate(float rate) {
		Rate = rate;
	}
	public float getUSD() {
		return USD;
	}
	public void setUSD(float uSD) {
		USD = uSD;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
