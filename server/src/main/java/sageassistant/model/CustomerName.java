package sageassistant.model;

import sageassistant.model.base.ModelTemplate;

public class CustomerName extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String CustomerCode;
	private String CustomerName;

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setCustomerrCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

}
