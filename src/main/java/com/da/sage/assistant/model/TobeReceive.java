/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:22:45                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/TobeReceive.java                               *
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
public class TobeReceive extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String ProjectNO;
    private String PurchaseNO;
    private String Line;
    private String VendorCode;
    private String VendorName;
    private String PN;
    private Integer Qty;
    private String Unit;
    private String Description;
    private BigDecimal NetPrice;
    private String Currency;
    private BigDecimal USD;
    private Float Rate;
    private Date OrderDate;
    private Date AckDate;
    private Date ExpectDate;
    private String CreateUser;
}
