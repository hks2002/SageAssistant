package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class PnStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private String PN;
	private String Desc1;
	private String Desc2;
	private String Desc3;
	private String PNStatus;
	private String SalesOrderNO;
	private String CustomerCode;
	private String CustomerName;
	public String getPN() {
		return PN;
	}
	public void setPN(String pN) {
		PN = pN;
	}
	public String getDesc1() {
		return Desc1;
	}
	public void setDesc1(String desc1) {
		Desc1 = desc1;
	}
	public String getDesc2() {
		return Desc2;
	}
	public void setDesc2(String desc2) {
		Desc2 = desc2;
	}
	public String getDesc3() {
		return Desc3;
	}
	public void setDesc3(String desc3) {
		Desc3 = desc3;
	}
	public String getPNStatus() {
		return PNStatus;
	}
	public void setPNStatus(String pNStatus) {
		PNStatus = pNStatus;
	}
	public String getSalesOrderNO() {
		return SalesOrderNO;
	}
	public void setSalesOrderNO(String salesOrderNO) {
		SalesOrderNO = salesOrderNO;
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
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
