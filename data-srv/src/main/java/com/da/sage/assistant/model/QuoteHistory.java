/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:21:33                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/QuoteHistory.java                              *
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
public class QuoteHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String SalesSite;
    private Integer OrderFlag;
    private String OrderNO;
    private String PN;
    private BigDecimal NetPrice;
    private String Currency;
    private Integer Qty;
    private String CustomerName;
    private String CustomerCode;
    private Date QuoteDate;
    private String QuoteNO;
    private BigDecimal USD;
    private Float Rate;
    private String TradeTerm;
}
