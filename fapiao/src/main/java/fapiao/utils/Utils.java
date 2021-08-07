package fapiao.utils;

import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

public class Utils {

	public static <T> T map2bean(Map<String, Object> map, T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}
	
	public static String list2String(List<String> list) {
		String s="[";
		for (String o : list ) {
			s += "\"" + o+"\",";
		}
		if (list.size()>0) {
			s = s.substring(0, s.lastIndexOf(','));
		}
		s += "]";
		return s;
	}
}
