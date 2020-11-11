package sageassistant.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class QuoteHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	private String SalesSite;
	private Integer OrderFlag;
	private String OrderNO;
	private String PN;
	private Float NetPrice;
	private String Currency;
	private Integer Qty;
	private String CustomerName;
	private String CustomerCode;
	private Date QuoteDate;
	private String QuoteNO;
	private Float USD;
	private Float Rate;

	public Integer getOrderFlag() {
		return OrderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		OrderFlag = orderFlag;
	}

	public String getSalesSite() {
		return SalesSite;
	}

	public void setSalesSite(String salesSite) {
		SalesSite = salesSite;
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

	public void setPN(String pN) {
		PN = pN;
	}

	public Float getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(Float netPrice) {
		NetPrice = netPrice;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public Integer getQty() {
		return Qty;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public Date getQuoteDate() {
		return QuoteDate;
	}

	public void setQuoteDate(Date quoteDate) {
		QuoteDate = quoteDate;
	}

	public String getQuoteNO() {
		return QuoteNO;
	}

	public void setQuoteNO(String quoteNO) {
		QuoteNO = quoteNO;
	}

	public Float getUSD() {
		return USD;
	}

	public void setUSD(Float USD) {
		USD = USD;
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
