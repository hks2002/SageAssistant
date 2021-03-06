package sageassistant.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class CostHistory implements Serializable {

	private static final long serialVersionUID = 1L;
	private String PurchaseSite;
	private String ProjectNO;
	private String OrderPN;
	private Date OrderDate;
	private String PurchaseNO;
	private String Line;
	private String VendorCode;
	private String VendorName;
	private String PurchasePN;
	private String Description;
	private String Currency;
	private Float NetPrice;
	private Integer Qty;
	private Float USD;
	private Float Rate;
	
	public String getPurchaseSite() {
		return PurchaseSite;
	}

	public void setPurchaseSite(String purchaseSite) {
		PurchaseSite = purchaseSite;
	}

	public String getProjectNO() {
		return ProjectNO;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public String getOrderPN() {
		return OrderPN;
	}

	public void setOrderPN(String orderPN) {
		OrderPN = orderPN;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public String getPurchaseNO() {
		return PurchaseNO;
	}

	public void setPurchaseNO(String purchaseNO) {
		PurchaseNO = purchaseNO;
	}

	public String getLine() {
		return Line;
	}

	public void setLine(String line) {
		Line = line;
	}

	public String getVendorCode() {
		return VendorCode;
	}

	public void setVendorCode(String vendorCode) {
		VendorCode = vendorCode;
	}

	public String getVendorName() {
		return VendorName;
	}

	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}

	public String getPurchasePN() {
		return PurchasePN;
	}

	public void setPurchasePN(String purchasePN) {
		PurchasePN = purchasePN;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public Float getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(Float netPrice) {
		NetPrice = netPrice;
	}

	public Integer getQty() {
		return Qty;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public Float getUSD() {
		return USD;
	}

	public void setUSD(Float usd) {
		USD = usd;
	}

	public Float getRate() {
		return Rate;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
