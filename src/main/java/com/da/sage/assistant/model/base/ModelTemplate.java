/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:29                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:19:16                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/base/ModelTemplate.java                        *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import java.io.Serializable;

/**
 * Set field name style to 'Pascal'
 * Change 'BigDecimal' to 'Double'
 * Set Date format to 'yyyy-MM-dd'
 */
public class ModelTemplate implements Serializable {

    public static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JSON.toJSONString(
            this,
            SerializeConfig.globalInstance,
            new SerializeFilter[] { new PascalNameFilter(), new BigDecimalValueFilter() },
            "yyyy-MM-dd",
            JSON.DEFAULT_GENERATE_FEATURE
        );
    }
}
