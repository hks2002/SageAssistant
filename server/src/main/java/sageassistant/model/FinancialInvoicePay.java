package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class FinancialInvoicePay extends ModelTemplate {

	private static final long serialVersionUID = 1L;

	private String Site;
	private String Currency;
	private String Custom;
	private String Name;
	private String InvoiceNO;
	private BigDecimal Amount;
	private BigDecimal AmountLocal;
	private BigDecimal Pay;
	private BigDecimal PayLocal;
	private Date CreateDate;
	private Date DueDate;
	private Date PayDate;
	private String Fapiao;
	private String CustRef;
	private String OrderNO;
	private String Status;

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getCustom() {
		return Custom;
	}

	public void setCustom(String custom) {
		Custom = custom;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getInvoiceNO() {
		return InvoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		InvoiceNO = invoiceNO;
	}

	public BigDecimal getAmount() {
		return Amount;
	}

	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}

	public BigDecimal getAmountLocal() {
		return AmountLocal;
	}

	public void setAmountLocal(BigDecimal amountLocal) {
		AmountLocal = amountLocal;
	}

	public BigDecimal getPay() {
		return Pay;
	}

	public void setPay(BigDecimal pay) {
		Pay = pay;
	}

	public BigDecimal getPayLocal() {
		return PayLocal;
	}

	public void setPayLocal(BigDecimal payLocal) {
		PayLocal = payLocal;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public Date getPayDate() {
		return PayDate;
	}

	public void setPayDate(Date payDate) {
		PayDate = payDate;
	}

	public String getFapiao() {
		return Fapiao;
	}

	public void setFapiao(String fapiao) {
		Fapiao = fapiao;
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

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
