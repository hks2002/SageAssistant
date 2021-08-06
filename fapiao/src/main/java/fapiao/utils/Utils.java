package fapiao.utils;

import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

public class Utils {

	public static <T> T map2bean(Map<String, Object> map, T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}
}
