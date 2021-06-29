package sageassistant.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class DeliveryDuration implements Serializable {

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

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
