/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-27 17:16:47
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:00:15
 * @FilePath       : \server\src\main\java\sageassistant\model\FinancialInvoicePayPro.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class FinancialInvoicePayPro extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Site;
    private String Currency;
    private String Customer;
    private String Name;
    private String InvoiceNO;
    private BigDecimal Amount;
    private BigDecimal AmountLocal;
    private BigDecimal Pay;
    private BigDecimal PayLocal;
    private Date CreateDate;
    private Date DueDate;
    private Date PayDate;
    private String Fapiao;
    private String CustRef;
    private String OrderNO;
    private String Status;
    private String MatchedBy;
    private String PayNO;
}
