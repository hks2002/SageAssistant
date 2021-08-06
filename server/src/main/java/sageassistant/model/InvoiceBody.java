package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class InvoiceBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer Line;
	private String PN;
	private String Description;
	private String Unit;
	private Float NetPrice;
	private Float AmountNoTax;
	private Float AmountTaxInclude;
	private Float AmountTax;
	private Float TaxRate;

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

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public Float getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(Float netPrice) {
		NetPrice = netPrice;
	}

	public Float getAmountNoTax() {
		return AmountNoTax;
	}

	public void setAmountNoTax(Float amountNoTax) {
		AmountNoTax = amountNoTax;
	}

	public Float getAmountTaxInclude() {
		return AmountTaxInclude;
	}

	public void setAmountTaxInclude(Float amountTaxInclude) {
		AmountTaxInclude = amountTaxInclude;
	}

	public Float getAmountTax() {
		return AmountTax;
	}

	public void setAmountTax(Float amountTax) {
		AmountTax = amountTax;
	}

	public Float getTaxRate() {
		return TaxRate;
	}

	public void setTaxRate(Float taxRate) {
		TaxRate = taxRate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
