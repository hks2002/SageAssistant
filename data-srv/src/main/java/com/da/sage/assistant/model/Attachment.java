/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:19:38                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/Attachment.java                                *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String PN;
    private String DocType;
    private String Path;
    private String File;
    private String Cat;
}
