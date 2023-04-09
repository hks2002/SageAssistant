/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:20:56                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/FinancialMonthBalance.java                     *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialMonthBalance extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String AccountNO;
    private String Currency;
    private String Year;
    private String Month;
    private BigDecimal M;
    private BigDecimal B;
    private BigDecimal C;
    private BigDecimal D;
}
