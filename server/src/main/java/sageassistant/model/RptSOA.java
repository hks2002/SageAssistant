package sageassistant.model;

import java.sql.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class RptSOA {
	private String Site;
	private String Customer;
	private String Name0;
	private String Name1;
	private String CustRef;
	private Float OrderTotal;
	private String OrderNO;
	private String InvoiceNO;
	private String Currency;
	private Float InvoiceValue;
	private Date InvoiceDate;
	private Date DueDate;
	private Integer Delay;
	private String Description;
	private Float PaidFC;
	private Float PrePaidCC;
	private Float PrePaidCCLine;

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

	public String getName1() {
		return Name1;
	}

	public void setName1(String name1) {
		Name1 = name1;
	}

	public String getCustRef() {
		return CustRef;
	}

	public void setCustRef(String custRef) {
		CustRef = custRef;
	}

	public Float getOrderTotal() {
		return OrderTotal;
	}

	public void setOrderTotal(Float orderTotal) {
		OrderTotal = orderTotal;
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

	public Float getInvoiceValue() {
		return InvoiceValue;
	}

	public void setInvoiceValue(Float invoiceValue) {
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Float getPaidFC() {
		return PaidFC;
	}

	public void setPaidFC(Float paidFC) {
		PaidFC = paidFC;
	}

	public Float getPrePaidCC() {
		return PrePaidCC;
	}

	public void setPrePaidCC(Float prePaidCC) {
		PrePaidCC = prePaidCC;
	}

	public Float getPrePaidCCLine() {
		return PrePaidCCLine;
	}

	public void setPrePaidCCLine(Float prePaidCCLine) {
		PrePaidCCLine = prePaidCCLine;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}