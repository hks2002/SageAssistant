package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SupplyName implements Serializable {
	private static final long serialVersionUID = 1L;

	private String SupplyCode;
	private String SupplyName;
	
	public String getSupplyCode() {
		return SupplyCode;
	}
	public void setSupplyCode(String supplyCode) {
		SupplyCode = supplyCode;
	}
	
	public String getSupplyName() {
		return SupplyName;
	}
	public void setSupplyName(String supplyName) {
		SupplyName = supplyName;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
