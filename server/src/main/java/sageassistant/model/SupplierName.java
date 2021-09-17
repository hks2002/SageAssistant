package sageassistant.model;

public class SupplierName extends AmodelTemplate {
	private static final long serialVersionUID = 1L;

	private String SupplierCode;
	private String SupplierName;

	public String getSupplierCode() {
		return SupplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
	}

	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

}
