package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class DeliveryDuration implements Serializable {

	private static final long serialVersionUID = 1L;

	private String SalesSite;
	private Integer Min;
	private Integer Avg;
	private Integer Max;

	public String getSalesSite() {
		return SalesSite;
	}

	public void setSalesSite(String salesSite) {
		SalesSite = salesSite;
	}

	public Integer getMin() {
		return Min;
	}

	public void setMin(Integer min) {
		Min = min;
	}

	public Integer getAvg() {
		return Avg;
	}

	public void setAvg(Integer avg) {
		Avg = avg;
	}

	public Integer getMax() {
		return Max;
	}

	public void setMax(Integer max) {
		Max = max;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
