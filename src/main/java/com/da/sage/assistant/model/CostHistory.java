/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:19:42                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/CostHistory.java                               *
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
public class CostHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String PurchaseSite;
    private String ProjectNO;
    private String OrderPN;
    private Date OrderDate;
    private String PurchaseNO;
    private String Line;
    private String VendorCode;
    private String VendorName;
    private String PurchasePN;
    private String Description;
    private String Currency;
    private BigDecimal NetPrice;
    private Integer Qty;
    private BigDecimal USD;
    private Float Rate;
}
