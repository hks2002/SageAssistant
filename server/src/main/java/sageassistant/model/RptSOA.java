package sageassistant.model;

import java.math.BigDecimal;
import java.sql.Date;

public class RptSOA {
	private String Site;
	private String Customer;
	private String Name0;
	private String CustRef;
	private String OrderNO;
	private String InvoiceNO;
	private String Currency;
	private BigDecimal InvoiceValue;
	private Date InvoiceDate;
	private Date DueDate;
	private Integer Delay;
	private BigDecimal Paid;
	private BigDecimal PaidSO;

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public String getName0() {
		return Name0;
	}

	public void setName0(String name0) {
		Name0 = name0;
	}

	public String getCustRef() {
		return CustRef;
	}

	public void setCustRef(String custRef) {
		CustRef = custRef;
	}

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public String getInvoiceNO() {
		return InvoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		InvoiceNO = invoiceNO;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public BigDecimal getInvoiceValue() {
		return InvoiceValue;
	}

	public void setInvoiceValue(BigDecimal invoiceValue) {
		InvoiceValue = invoiceValue;
	}

	public Date getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Integer getDelay() {
		return Delay;
	}

	public void setDelay(Integer delay) {
		Delay = delay;
	}

	public BigDecimal getPaid() {
		return Paid;
	}

	public void setPaid(BigDecimal paid) {
		Paid = paid;
	}

	public BigDecimal getPaidSO() {
		return PaidSO;
	}

	public void setPaidSO(BigDecimal paidSO) {
		PaidSO = paidSO;
	}

}