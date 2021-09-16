package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class FinancialBalance implements Serializable {

	private static final long serialVersionUID = 1L;

	private String AccountNO;
	private String Currency;
	private String Year;
	private Float B0;
	private Float B1;
	private Float B2;
	private Float B3;
	private Float B4;
	private Float B5;
	private Float B6;
	private Float B7;
	private Float B8;
	private Float B9;
	private Float B10;
	private Float B11;
	private Float B12;
	private Float C0;
	private Float C1;
	private Float C2;
	private Float C3;
	private Float C4;
	private Float C5;
	private Float C6;
	private Float C7;
	private Float C8;
	private Float C9;
	private Float C10;
	private Float C11;
	private Float C12;
	private Float D0;
	private Float D1;
	private Float D2;
	private Float D3;
	private Float D4;
	private Float D5;
	private Float D6;
	private Float D7;
	private Float D8;
	private Float D9;
	private Float D10;
	private Float D11;
	private Float D12;
	
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
	public Float getB0() {
		return B0;
	}
	public void setB0(Float b0) {
		B0 = b0;
	}
	public Float getB1() {
		return B1;
	}
	public void setB1(Float b1) {
		B1 = b1;
	}
	public Float getB2() {
		return B2;
	}
	public void setB2(Float b2) {
		B2 = b2;
	}
	public Float getB3() {
		return B3;
	}
	public void setB3(Float b3) {
		B3 = b3;
	}
	public Float getB4() {
		return B4;
	}
	public void setB4(Float b4) {
		B4 = b4;
	}
	public Float getB5() {
		return B5;
	}
	public void setB5(Float b5) {
		B5 = b5;
	}
	public Float getB6() {
		return B6;
	}
	public void setB6(Float b6) {
		B6 = b6;
	}
	public Float getB7() {
		return B7;
	}
	public void setB7(Float b7) {
		B7 = b7;
	}
	public Float getB8() {
		return B8;
	}
	public void setB8(Float b8) {
		B8 = b8;
	}
	public Float getB9() {
		return B9;
	}
	public void setB9(Float b9) {
		B9 = b9;
	}
	public Float getB10() {
		return B10;
	}
	public void setB10(Float b10) {
		B10 = b10;
	}
	public Float getB11() {
		return B11;
	}
	public void setB11(Float b11) {
		B11 = b11;
	}
	public Float getB12() {
		return B12;
	}
	public void setB12(Float b12) {
		B12 = b12;
	}
	public Float getC0() {
		return C0;
	}
	public void setC0(Float c0) {
		C0 = c0;
	}
	public Float getC1() {
		return C1;
	}
	public void setC1(Float c1) {
		C1 = c1;
	}
	public Float getC2() {
		return C2;
	}
	public void setC2(Float c2) {
		C2 = c2;
	}
	public Float getC3() {
		return C3;
	}
	public void setC3(Float c3) {
		C3 = c3;
	}
	public Float getC4() {
		return C4;
	}
	public void setC4(Float c4) {
		C4 = c4;
	}
	public Float getC5() {
		return C5;
	}
	public void setC5(Float c5) {
		C5 = c5;
	}
	public Float getC6() {
		return C6;
	}
	public void setC6(Float c6) {
		C6 = c6;
	}
	public Float getC7() {
		return C7;
	}
	public void setC7(Float c7) {
		C7 = c7;
	}
	public Float getC8() {
		return C8;
	}
	public void setC8(Float c8) {
		C8 = c8;
	}
	public Float getC9() {
		return C9;
	}
	public void setC9(Float c9) {
		C9 = c9;
	}
	public Float getC10() {
		return C10;
	}
	public void setC10(Float c10) {
		C10 = c10;
	}
	public Float getC11() {
		return C11;
	}
	public void setC11(Float c11) {
		C11 = c11;
	}
	public Float getC12() {
		return C12;
	}
	public void setC12(Float c12) {
		C12 = c12;
	}
	public Float getD0() {
		return D0;
	}
	public void setD0(Float d0) {
		D0 = d0;
	}
	public Float getD1() {
		return D1;
	}
	public void setD1(Float d1) {
		D1 = d1;
	}
	public Float getD2() {
		return D2;
	}
	public void setD2(Float d2) {
		D2 = d2;
	}
	public Float getD3() {
		return D3;
	}
	public void setD3(Float d3) {
		D3 = d3;
	}
	public Float getD4() {
		return D4;
	}
	public void setD4(Float d4) {
		D4 = d4;
	}
	public Float getD5() {
		return D5;
	}
	public void setD5(Float d5) {
		D5 = d5;
	}
	public Float getD6() {
		return D6;
	}
	public void setD6(Float d6) {
		D6 = d6;
	}
	public Float getD7() {
		return D7;
	}
	public void setD7(Float d7) {
		D7 = d7;
	}
	public Float getD8() {
		return D8;
	}
	public void setD8(Float d8) {
		D8 = d8;
	}
	public Float getD9() {
		return D9;
	}
	public void setD9(Float d9) {
		D9 = d9;
	}
	public Float getD10() {
		return D10;
	}
	public void setD10(Float d10) {
		D10 = d10;
	}
	public Float getD11() {
		return D11;
	}
	public void setD11(Float d11) {
		D11 = d11;
	}
	public Float getD12() {
		return D12;
	}
	public void setD12(Float d12) {
		D12 = d12;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}

}
