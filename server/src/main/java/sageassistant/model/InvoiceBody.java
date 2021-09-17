package sageassistant.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class InvoiceBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer Line;
	private String PN;
	private String Description;
	private Integer Qty;
	private String Unit;
	private BigDecimal NetPrice;
	private BigDecimal AmountNoTax;
	private BigDecimal AmountTaxInclude;
	private BigDecimal AmountTax;
	private BigDecimal TaxRate;

	public Integer getLine() {
		return Line;
	}

	public void setLine(Integer line) {
		Line = line;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Integer getQty() {
		return Qty;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public BigDecimal getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(BigDecimal netPrice) {
		NetPrice = netPrice;
	}

	public BigDecimal getAmountNoTax() {
		return AmountNoTax;
	}

	public void setAmountNoTax(BigDecimal amountNoTax) {
		AmountNoTax = amountNoTax;
	}

	public BigDecimal getAmountTaxInclude() {
		return AmountTaxInclude;
	}

	public void setAmountTaxInclude(BigDecimal amountTaxInclude) {
		AmountTaxInclude = amountTaxInclude;
	}

	public BigDecimal getAmountTax() {
		return AmountTax;
	}

	public void setAmountTax(BigDecimal amountTax) {
		AmountTax = amountTax;
	}

	public BigDecimal getTaxRate() {
		return TaxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		TaxRate = taxRate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
