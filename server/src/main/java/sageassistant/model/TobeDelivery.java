package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class TobeDelivery extends ModelTemplate {
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

	public String getProjectNO() {
		return ProjectNO;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public Integer getQty() {
		return Qty;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
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

	public BigDecimal getUSD() {
		return USD;
	}

	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}

	public Float getRate() {
		return Rate;
	}

	public void setRate(Float rate) {
		Rate = rate;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public Date getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(Date requestDate) {
		RequestDate = requestDate;
	}

	public Date getPlanedDate() {
		return PlanedDate;
	}

	public void setPlanedDate(Date planedDate) {
		PlanedDate = planedDate;
	}

}