/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:21:48                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/StockSummary.java                              *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockSummary extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String G;
    private String A;
    private String Location;
    private String PN;
    private String Description;
    private String OptionPN;
    private Float Qty;
    private BigDecimal Cost;
}
