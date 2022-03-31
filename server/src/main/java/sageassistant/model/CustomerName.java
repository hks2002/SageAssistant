package sageassistant.model;

import sageassistant.model.base.ModelTemplate;

public class CustomerName extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String CustomerrCode;
	private String CustomerName;

	public String getCustomerrCode() {
		return CustomerrCode;
	}

	public void setCustomerrCode(String customerrCode) {
		CustomerrCode = customerrCode;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

}
