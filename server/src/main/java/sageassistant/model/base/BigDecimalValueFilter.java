/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 15:19:24
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:54:53
 * @FilePath       : \server\src\main\java\sageassistant\model\base\BigDecimalValueFilter.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model.base;

import com.alibaba.fastjson.serializer.ValueFilter;
import java.math.BigDecimal;

/**
 * Change BigDecimal to Double
 */
public class BigDecimalValueFilter implements ValueFilter {

    @Override
    public Object process(Object object, String name, Object value) {
        if (value != null && value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        return value;
    }
}
