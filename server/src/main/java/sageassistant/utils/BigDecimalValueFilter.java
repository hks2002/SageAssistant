package sageassistant.utils;

import java.math.BigDecimal;

import com.alibaba.fastjson.serializer.ValueFilter;

public class BigDecimalValueFilter implements ValueFilter {

	@Override
	public Object process(Object object, String name, Object value) {
		if (value != null && value instanceof BigDecimal) {
			return ((BigDecimal) value).doubleValue();
		}
		return value;
	}
}