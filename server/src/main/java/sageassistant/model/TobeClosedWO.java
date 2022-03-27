package sageassistant.model;

import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class TobeClosedWO extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String ProjectNO;
	private String OrderNO;
	private String WorkOrderNO;
	private String WOStatus;
	private String ProductionStatus;
	private String OrderType;
	private String CustomerCode;
	private String CustomerName;
	private String PN;
	private Integer Qty;
	private Date OrderDate;

	public String getProjectNO() {
		return ProjectNO;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public String getWorkOrderNO() {
		return WorkOrderNO;
	}

	public void setWorkOrderNO(String workOrderNO) {
		WorkOrderNO = workOrderNO;
	}

	public String getWOStatus() {
		return WOStatus;
	}

	public void setWOStatus(String wOStatus) {
		WOStatus = wOStatus;
	}

	public String getProductionStatus() {
		return ProductionStatus;
	}

	public void setProductionStatus(String productionStatus) {
		ProductionStatus = productionStatus;
	}

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
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

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public Integer getQty() {
		return Qty;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

}
