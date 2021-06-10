package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplierSummaryQty implements Serializable {
	private static final long serialVersionUID = 1L;

	private String Site;
	private String SupplierCode;
	private String CountType;
	private int Qty;

	public String getSupplierCode() {
		return SupplierCode;
	}

	public void setSupplierCode(String supplyCode) {
		SupplierCode = supplyCode;
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
