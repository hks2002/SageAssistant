package sageassistant.model;

import java.io.Serializable;
import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class TobeDealWithOrderLine implements Serializable {
	private static final long serialVersionUID = 1L;
    
	private String SalesOrderNO;
	private String ProjectNO;
	private String OrderType;
	private String OrderCategory;
	private String PN;
	private Integer Qty;
	private String Unit;
	private String Description;
	private String CustomerCode;
	private String CustomerName;
	private Date OrderDate;
	private Date DemandDate;

	public String getCustomerCode() {
		return CustomerCode;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public Date getDemandDate() {
		return DemandDate;
	}

	public String getDescription() {
		return Description;
	}

	public String getOrderCategory() {
		return OrderCategory;
	}

	public Date getOrderDate() {
		return OrderDate;
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

	public String getSalesOrderNO() {
		return SalesOrderNO;
	}

	public String getUnit() {
		return Unit;
	}

	public void setCustomerCode(String customerCode) {
		CustomerCode = customerCode;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public void setDemandDate(Date demandDate) {
		DemandDate = demandDate;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setOrderCategory(String orderCategory) {
		OrderCategory = orderCategory;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
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

	public void setSalesOrderNO(String salesOrderNO) {
		SalesOrderNO = salesOrderNO;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
