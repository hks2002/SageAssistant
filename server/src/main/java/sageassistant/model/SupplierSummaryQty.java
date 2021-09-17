package sageassistant.model;

public class SupplierSummaryQty extends AmodelTemplate {
	private static final long serialVersionUID = 1L;

	private String Site;
	private String SupplierCode;
	private String CountType;
	private int Qty;

	public String getSupplierCode() {
		return SupplierCode;
	}

	public void setSupplierCode(String supplyCode) {
		SupplierCode = supplyCode;
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
