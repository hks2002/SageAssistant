package sageassistant.model;

import sageassistant.model.base.ModelTemplate;

public class CustomerSummaryQty extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String Site;
	private String CustomerCode;
	private String CountType;
	private int Qty;

	public String getCustomerCode() {
		return CustomerCode;
	}

	public void setSupplierCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getCountType() {
		return CountType;
	}

	public void setCountType(String countType) {
		CountType = countType;
	}

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

}
