package sageassistant.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplierOpenItems implements Serializable {
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
	public int getDateNeed() {
		return DateNeed;
	}
	public void setDateNeed(int dateNeed) {
		DateNeed = dateNeed;
	}
	public int getDaysDelay() {
		return DaysDelay;
	}
	public void setDaysDelay(int daysDelay) {
		DaysDelay = daysDelay;
	}
	private int DateNeed;
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
