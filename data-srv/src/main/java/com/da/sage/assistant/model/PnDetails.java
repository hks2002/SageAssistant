/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:21:21                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/PnDetails.java                                 *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PnDetails extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private Integer ROWID;
    private String PNROOT;
    private String PN;
    private String Cat;
    private String Version;
    private String Comment;
    private String Desc1;
    private String Desc2;
    private String Desc3;
    private Integer Status;
    private Date CreateDate;
}
