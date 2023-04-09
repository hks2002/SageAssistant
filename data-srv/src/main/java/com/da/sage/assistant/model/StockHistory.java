/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:21:41                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/StockHistory.java                              *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Location;
    private String Seq;
    private String PN;
    private String Description;
    private Float Qty;
    private String Unit;
    private BigDecimal Cost;
    private String ProjectNO;
    private String SourceNO;
    private String SourceLine;
    private String EntryNO;
    private String EntryLine;
    private String CreateUser;
    private Date CreateDate;
}
