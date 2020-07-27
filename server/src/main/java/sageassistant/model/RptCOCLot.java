package sageassistant.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class RptCOCLot {
	String LOT_0;

	public String getLOT_0() {
		return LOT_0;
	}

	public void setLOT_0(String lOT_0) {
		LOT_0 = lOT_0;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
