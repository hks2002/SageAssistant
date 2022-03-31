package sageassistant.model;

import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class CustomerDeliveryHistory extends ModelTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Site;
	private String CustomerCode;
	private String OrderNO;
	private String ProjectNO;
	private String PN;
	private String Description;
	private Date OrderDate;
	private Date DemandDate;
	private Date ShipDate;
	private String DeliveryNO;
	private int DaysNeed;
	
	public String getSite() {
		return Site;
	}
	public void setSite(String site) {
		Site = site;
	}
	public String getCustomerCode() {
		return CustomerCode;
	}
	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}
	public String getOrderNO() {
		return OrderNO;
	}
	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
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
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public Date getDemandDate() {
		return DemandDate;
	}
	public void setDemandDate(Date demandDate) {
		DemandDate = demandDate;
	}
	public Date getShipDate() {
		return ShipDate;
	}
	public void setShipDate(Date shipDate) {
		ShipDate = shipDate;
	}
	public String getDeliveryNO() {
		return DeliveryNO;
	}
	public void setDeliveryNO(String deliveryNO) {
		DeliveryNO = deliveryNO;
	}
	public int getDaysNeed() {
		return DaysNeed;
	}
	public void setDaysNeed(int daysNeed) {
		DaysNeed = daysNeed;
	}

}
