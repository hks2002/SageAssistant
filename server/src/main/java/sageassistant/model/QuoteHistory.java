package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

public class QuoteHistory extends AmodelTemplate {

	private static final long serialVersionUID = 1L;

	private String SalesSite;
	private Integer OrderFlag;
	private String OrderNO;
	private String PN;
	private BigDecimal NetPrice;
	private String Currency;
	private Integer Qty;
	private String CustomerName;
	private String CustomerCode;
	private Date QuoteDate;
	private String QuoteNO;
	private BigDecimal USD;
	private Float Rate;
	private String TradeTerm;

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

	public BigDecimal getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
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

	public String getTradeTerm() {
		return TradeTerm;
	}

	public void setTradeTerm(String tradeTerm) {
		TradeTerm = tradeTerm;
	}

}
