package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class InvoiceHeader implements Serializable {

	private static final long serialVersionUID = 1L;

	private String Facility;
	private String Currency;
	private String InvoiceNO;
	private String CreateDate;
	private String CreateUser;
	private String Note;
	private String InvoiceStatus;
	private String FaPiao;
	private String Customer;
	private String Address;
	private Float AmountTaxInclude;
	private Float AmountTaxNotInclude;
	private Float AmountTax;
	private Float CurrRate;

	public String getFacility() {
		return Facility;
	}

	public void setFacility(String facility) {
		Facility = facility;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getInvoiceNO() {
		return InvoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		InvoiceNO = invoiceNO;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getInvoiceStatus() {
		return InvoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		InvoiceStatus = invoiceStatus;
	}

	public String getFaPiao() {
		return FaPiao;
	}

	public void setFaPiao(String faPiao) {
		FaPiao = faPiao;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Float getAmountTaxInclude() {
		return AmountTaxInclude;
	}

	public void setAmountTaxInclude(Float amountTaxInclude) {
		AmountTaxInclude = amountTaxInclude;
	}

	public Float getAmountTaxNotInclude() {
		return AmountTaxNotInclude;
	}

	public void setAmountTaxNotInclude(Float amountTaxNotInclude) {
		AmountTaxNotInclude = amountTaxNotInclude;
	}

	public Float getAmountTax() {
		return AmountTax;
	}

	public void setAmountTax(Float amountTax) {
		AmountTax = amountTax;
	}

	public Float getCurrRate() {
		return CurrRate;
	}

	public void setCurrRate(Float currRate) {
		CurrRate = currRate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
