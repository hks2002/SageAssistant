package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class TobeReceive extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String ProjectNO;
	private String PurchaseNO;
	private String Line;
	private String VendorCode;
	private String VendorName;
	private String PN;
	private Integer Qty;
	private String Unit;
	private String Description;
	private BigDecimal NetPrice;
	private String Currency;
	private BigDecimal USD;
	private Float Rate;
	private Date OrderDate;
	private Date AckDate;
	private Date ExpectDate;
	private String CreateUser;

	public Date getAckDate() {
		return AckDate;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public String getCurrency() {
		return Currency;
	}

	public String getDescription() {
		return Description;
	}

	public Date getExpectDate() {
		return ExpectDate;
	}

	public String getLine() {
		return Line;
	}

	public BigDecimal getNetPrice() {
		return NetPrice;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public String getPN() {
		return PN;
	}

	public String getProjectNO() {
		return ProjectNO;
	}

	public String getPurchaseNO() {
		return PurchaseNO;
	}

	public int getQty() {
		return Qty;
	}

	public Float getRate() {
		return Rate;
	}

	public String getUnit() {
		return Unit;
	}

	public String getVendorCode() {
		return VendorCode;
	}

	public String getVendorName() {
		return VendorName;
	}

	public void setAckDate(Date ackDate) {
		AckDate = ackDate;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setExpectDate(Date expectDate) {
		ExpectDate = expectDate;
	}

	public void setLine(String line) {
		Line = line;
	}

	public void setNetPrice(BigDecimal netPrice) {
		NetPrice = netPrice;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public void setPurchaseNO(String purchaseNO) {
		PurchaseNO = purchaseNO;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public void setVendorCode(String vendorCode) {
		VendorCode = vendorCode;
	}

	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}

	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}

}
