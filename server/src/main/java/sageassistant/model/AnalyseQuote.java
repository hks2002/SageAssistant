package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class AnalyseQuote extends ModelTemplate {

	private static final long serialVersionUID = 1L;

	private String QuoteNO;
	private Date QuoteDate;
	private String CustomerCode;
	private String CustomerName;
	private BigDecimal NetPrice;
	private String OrderNO;
	private Integer OrderFlag;
	private Integer QTY;
	private Integer ROWNUM;

	public String getQuoteNO() {
		return QuoteNO;
	}

	public void setQuoteNO(String quoteNO) {
		QuoteNO = quoteNO;
	}

	public Date getQuoteDate() {
		return QuoteDate;
	}

	public void setQuoteDate(Date quoteDate) {
		QuoteDate = quoteDate;
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

	public BigDecimal getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		NetPrice = netPrice;
	}

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public Integer getOrderFlag() {
		return OrderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		OrderFlag = orderFlag;
	}

	public Integer getQTY() {
		return QTY;
	}

	public void setQTY(Integer qty) {
		QTY = qty;
	}

	public Integer getROWNUM() {
		return ROWNUM;
	}

	public void setROWNUM(Integer rOWNUM) {
		ROWNUM = rOWNUM;
	}

}
