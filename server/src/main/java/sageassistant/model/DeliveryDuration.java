package sageassistant.model;

import java.util.Date;

import sageassistant.model.base.ModelTemplate;

public class DeliveryDuration extends ModelTemplate {

	private static final long serialVersionUID = 1L;

	private String SalesSite;
	private String PN;
	private Date OrderDate;
	private Date ShipDate;
	private Integer Duration;

	public String getSalesSite() {
		return SalesSite;
	}

	public void setSalesSite(String salesSite) {
		SalesSite = salesSite;
	}

	public String getPN() {
		return PN;
	}

	public void setPN(String pN) {
		PN = pN;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public Date getShipDate() {
		return ShipDate;
	}

	public void setShipDate(Date shipDate) {
		ShipDate = shipDate;
	}

	public Integer getDuration() {
		return Duration;
	}

	public void setDuration(Integer duration) {
		Duration = duration;
	}

}
