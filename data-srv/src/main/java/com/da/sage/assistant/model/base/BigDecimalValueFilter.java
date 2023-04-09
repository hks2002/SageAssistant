/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-25 15:19:24                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:52:56                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/model/base/BigDecimalValueFilter.java                *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model.base;

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
