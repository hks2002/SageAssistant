package sageassistant.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;

import sageassistant.utils.BigDecimalValueFilter;

public class AmodelTemplate implements Serializable {

	public static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializeConfig.globalInstance,
				new SerializeFilter[] { new PascalNameFilter(), new BigDecimalValueFilter() }, "yyyy-MM-dd",
				JSON.DEFAULT_GENERATE_FEATURE);
	}

}
