/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-09-21 12:36:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:19:34                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/AnalysesSales.java                             *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysesSales extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String OrderNO;
    private Date OrderDate;
    private String CustomerCode;
    private String CustomerName;
    private Double NetPrice;
    private Integer QTY;
    private Integer ROWNUM;
}
