package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class FinancialMonthBalance implements Serializable {

	private static final long serialVersionUID = 1L;

	private String AccountNO;
	private String Currency;
	private String Year;
	private String Month;
	private Float B;
	private Float C;
	private Float D;

	public String getAccountNO() {
		return AccountNO;
	}

	public void setAccountNO(String accountNO) {
		AccountNO = accountNO;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public Float getB() {
		return B;
	}

	public void setB(Float b) {
		B = b;
	}

	public Float getC() {
		return C;
	}

	public void setC(Float c) {
		C = c;
	}

	public Float getD() {
		return D;
	}

	public void setD(Float d) {
		D = d;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}

}
