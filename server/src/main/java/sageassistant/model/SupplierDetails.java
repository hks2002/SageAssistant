package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplierDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private String SupplierCode;
	private String SupplierName0;
	private String SupplierName1;
	private String Address0;
	private String Address1;
	private String PostCode;
	private String Country;
	private String State;
	private String City;
	private String Tel0;
	private String Tel1;
	private String Tel2;
	private String Tel3;
	private String Tel4;
	private String Fax0;
	private String Mobile0;
	private String Email0;
	private String Email1;
	private String Email2;
	private String Email3;
	private String Email4;
	private String Website;

	public String getSupplyCode() {
		return SupplierCode;
	}

	public void setSupplyCode(String supplierCode) {
		SupplierCode = supplierCode;
	}

	public String getSupplyName0() {
		return SupplierName0;
	}

	public void setSupplyName0(String supplierName0) {
		SupplierName0 = supplierName0;
	}

	public String getSupplyName1() {
		return SupplierName1;
	}

	public void setSupplyName1(String supplierName1) {
		SupplierName1 = supplierName1;
	}

	public String getAddress0() {
		return Address0;
	}

	public void setAddress0(String address0) {
		Address0 = address0;
	}

	public String getAddress1() {
		return Address1;
	}

	public void setAddress1(String address1) {
		Address1 = address1;
	}

	public String getPostCode() {
		return PostCode;
	}

	public void setPostCode(String postCode) {
		PostCode = postCode;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getTel0() {
		return Tel0;
	}

	public void setTel0(String tel0) {
		Tel0 = tel0;
	}

	public String getTel1() {
		return Tel1;
	}

	public void setTel1(String tel1) {
		Tel1 = tel1;
	}

	public String getTel2() {
		return Tel2;
	}

	public void setTel2(String tel2) {
		Tel2 = tel2;
	}

	public String getTel3() {
		return Tel3;
	}

	public void setTel3(String tel3) {
		Tel3 = tel3;
	}

	public String getTel4() {
		return Tel4;
	}

	public void setTel4(String tel4) {
		Tel4 = tel4;
	}

	public String getFax0() {
		return Fax0;
	}

	public void setFax0(String fax0) {
		Fax0 = fax0;
	}

	public String getMobile0() {
		return Mobile0;
	}

	public void setMobile0(String mobile0) {
		Mobile0 = mobile0;
	}

	public String getEmail0() {
		return Email0;
	}

	public void setEmail0(String email0) {
		Email0 = email0;
	}

	public String getEmail1() {
		return Email1;
	}

	public void setEmail1(String email1) {
		Email1 = email1;
	}

	public String getEmail2() {
		return Email2;
	}

	public void setEmail2(String email2) {
		Email2 = email2;
	}

	public String getEmail3() {
		return Email3;
	}

	public void setEmail3(String email3) {
		Email3 = email3;
	}

	public String getEmail4() {
		return Email4;
	}

	public void setEmail4(String email4) {
		Email4 = email4;
	}

	public String getWebsite() {
		return Website;
	}

	public void setWebsite(String website) {
		Website = website;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
