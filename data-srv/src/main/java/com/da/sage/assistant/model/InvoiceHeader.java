/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:21:06                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/InvoiceHeader.java                             *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceHeader extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Facility;
    private String Currency;
    private String InvoiceNO;
    private String CreateDate;
    private String CreateUser;
    private String Note;
    private String InvoiceStatus;
    private String FaPiao;
    private String Customer;
    private String Address;
    private BigDecimal AmountTaxInclude;
    private BigDecimal AmountTaxNotInclude;
    private BigDecimal AmountTax;
    private Float CurrRate;
}
