package sageassistant.model;

import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class TobePurchaseBom extends ModelTemplate {
	private static final long serialVersionUID = 1L;

	private String ProjectNO;
	private String OrderType;
	private String WorkOrderNO;
	private String BomSeq;
	private String CustomerCode;
	private String CustomerName;
	private String ForPN;
	private String PN;
	private String Description;
	private Integer Qty;
	private Integer ShortQty;
	private Integer AllQty;
	private String Unit;
	private Date CreateDate;
	private String CreateUser;

	public Integer getAllQty() {
		return AllQty;
	}

	public String getBomSeq() {
		return BomSeq;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public String getCustomerCode() {
		return CustomerCode;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public String getDescription() {
		return Description;
	}

	public String getForPN() {
		return ForPN;
	}

	public String getOrderType() {
		return OrderType;
	}

	public String getPN() {
		return PN;
	}

	public String getProjectNO() {
		return ProjectNO;
	}

	public Integer getQty() {
		return Qty;
	}

	public Integer getShortQty() {
		return ShortQty;
	}

	public String getUnit() {
		return Unit;
	}

	public String getWorkOrderNO() {
		return WorkOrderNO;
	}

	public void setAllQty(Integer allQty) {
		AllQty = allQty;
	}

	public void setBomSeq(String bomSeq) {
		BomSeq = bomSeq;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setForPN(String forPN) {
		ForPN = forPN;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public void setProjectNO(String projectNO) {
		ProjectNO = projectNO;
	}

	public void setQty(Integer qty) {
		Qty = qty;
	}

	public void setShortQty(Integer shortQty) {
		ShortQty = shortQty;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public void setWorkOrderNO(String workOrderNO) {
		WorkOrderNO = workOrderNO;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}
}