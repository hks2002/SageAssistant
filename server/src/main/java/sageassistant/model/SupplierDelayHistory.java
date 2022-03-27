package sageassistant.model;

import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class SupplierDelayHistory extends ModelTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Site;
	private String SupplierCode;
	private String PurchaseNO;
	private String ProjectNO;
	private String PN;
	private String Description;
	private Date AckDate;
	private Date ExpectDate;
	private Date OrderDate;
	private String ReceiptNO;
	private Date ReceiptDate;
	private int DaysDelay;

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getSupplierCode() {
		return SupplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		SupplierCode = supplierCode;
	}

	public String getPurchaseNO() {
		return PurchaseNO;
	}

	public void setPurchaseNO(String purchaseNO) {
		PurchaseNO = purchaseNO;
	}

	public String getProjectNO() {
		return ProjectNO;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getAckDate() {
		return AckDate;
	}

	public void setAckDate(Date ackDate) {
		AckDate = ackDate;
	}

	public Date getExpectDate() {
		return ExpectDate;
	}

	public void setExpectDate(Date expectDate) {
		ExpectDate = expectDate;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public String getReceiptNO() {
		return ReceiptNO;
	}

	public void setReceiptNO(String receiptNO) {
		ReceiptNO = receiptNO;
	}

	public Date getReceiptDate() {
		return ReceiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		ReceiptDate = receiptDate;
	}

	public int getDaysDelay() {
		return DaysDelay;
	}

	public void setDaysDelay(int daysDelay) {
		DaysDelay = daysDelay;
	}

}
