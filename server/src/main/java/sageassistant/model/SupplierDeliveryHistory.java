package sageassistant.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplierDeliveryHistory implements Serializable {
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
	private int DaysNeed;

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getSupplyCode() {
		return SupplierCode;
	}

	public void setSupplyCode(String supplyCode) {
		SupplierCode = supplyCode;
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

	public int getDaysNeed() {
		return DaysNeed;
	}

	public void setDaysNeed(int daysNeed) {
		DaysNeed = daysNeed;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}

}
