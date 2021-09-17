package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

public class TobeDelivery extends AmodelTemplate {
	private static final long serialVersionUID = 1L;

	private String ProjectNO;
	private String OrderNO;
	private String OrderType;
	private String PN;
	private Integer Qty;
	private String Description;
	private String CustomerCode;
	private String CustomerName;
	private String Currency;
	private BigDecimal NetPrice;
	private BigDecimal USD;
	private Float Rate;
	private Date OrderDate;
	private Date RequestDate;
	private Date PlanedDate;

	public String getCurrency() {
		return Currency;
	}

	public String getCustomerCode() {
		return CustomerCode;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public String getDescription() {
		return Description;
	}

	public BigDecimal getNetPrice() {
		return NetPrice;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public String getOrderType() {
		return OrderType;
	}

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public String getPN() {
		return PN;
	}

	public String getProjectNO() {
		return ProjectNO;
	}

	public Integer getQty() {
		return Qty;
	}

	public Float getRate() {
		return Rate;
	}

	public Date getRequestDate() {
		return RequestDate;
	}

	public BigDecimal getUSD() {
		return USD;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setNetPrice(BigDecimal netPrice) {
		NetPrice = netPrice;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public void setProjectNo(String projectNO) {
		ProjectNO = projectNO;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

	public void setRequestDate(Date requestDate) {
		RequestDate = requestDate;
	}

	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}

	public Date getPlanedDate() {
		return PlanedDate;
	}

	public void setPlanedDate(Date planedDate) {
		PlanedDate = planedDate;
	}
}