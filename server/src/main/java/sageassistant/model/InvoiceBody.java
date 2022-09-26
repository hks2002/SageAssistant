/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:00:26
 * @FilePath       : \server\src\main\java\sageassistant\model\InvoiceBody.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class InvoiceBody extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private Integer Line;
    private String PN;
    private String Description;
    private Integer Qty;
    private String Unit;
    private BigDecimal NetPrice;
    private BigDecimal AmountNoTax;
    private BigDecimal AmountTaxInclude;
    private BigDecimal AmountTax;
    private BigDecimal TaxRate;
}
