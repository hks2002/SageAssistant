/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:00:20
 * @FilePath       : \server\src\main\java\sageassistant\model\FinancialMonthBalance.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

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
