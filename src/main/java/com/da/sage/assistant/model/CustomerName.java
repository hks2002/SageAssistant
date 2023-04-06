/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-31 16:17:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-10 17:19:22                                                                      *
 * @FilePath              : src/main/java/sageassistant/model/CustomerName.java                                      *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerName extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String CustomerCode;
    private String CustomerName;
}
