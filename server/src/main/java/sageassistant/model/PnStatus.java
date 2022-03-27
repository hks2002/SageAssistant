package sageassistant.model;

import sageassistant.model.base.ModelTemplate;

public class PnStatus extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String PN;
	private String Desc1;
	private String Desc2;
	private String Desc3;
	private String PNStatus;
	private String WC;
	private String ProjectNO;
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

	public String getProjectNO() {
		return ProjectNO;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public String getWC() {
		return WC;
	}

	public void setWC(String wC) {
		WC = wC;
	}
}
