package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

public class CostHistory extends AmodelTemplate {

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
	private BigDecimal NetPrice;
	private Integer Qty;
	private BigDecimal USD;
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

	public BigDecimal getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		NetPrice = netPrice;
	}

	public Integer getQty() {
		return Qty;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal usd) {
		USD = usd;
	}

	public Float getRate() {
		return Rate;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

}
