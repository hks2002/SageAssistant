package sageassistant.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class RptCOCSerial {
	String SERNUM_0;

	public String getSERNUM_0() {
		return SERNUM_0;
	}

	public void setSERNUM_0(String sERNUM_0) {
		SERNUM_0 = sERNUM_0;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance, new SerializeFilter[] { new PascalNameFilter() },
				"yyyy-MM-dd", JSON.DEFAULT_GENERATE_FEATURE);
	}
}
