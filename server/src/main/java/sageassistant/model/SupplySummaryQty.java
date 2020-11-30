package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplySummaryQty implements Serializable {
	private static final long serialVersionUID = 1L;

	private String Site;
	private String SupplyCode;
	private String CountType;
	private int Qty;

	public String getSupplyCode() {
		return SupplyCode;
	}

	public void setSupplyCode(String supplyCode) {
		SupplyCode = supplyCode;
	}

	public String getSite() {
		return Site;
	}

	public void setSite(String site) {
		Site = site;
	}

	public String getCountType() {
		return CountType;
	}

	public void setCountType(String countType) {
		CountType = countType;
	}

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
