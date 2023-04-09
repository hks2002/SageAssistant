/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-09-21 12:32:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:19:25                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/AnalysesQuote.java                             *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysesQuote extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String QuoteNO;
    private Date QuoteDate;
    private String CustomerCode;
    private String CustomerName;
    private Double NetPrice;
    private String OrderNO;
    private Integer OrderFlag;
    private Integer QTY;
    private Integer ROWNUM;
}
