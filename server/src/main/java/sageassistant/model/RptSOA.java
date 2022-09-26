/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:02:10
 * @FilePath       : \server\src\main\java\sageassistant\model\RptSOA.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RptSOA {

    private String Site;
    private String Customer;
    private String Name0;
    private String CustRef;
    private String OrderNO;
    private String InvoiceNO;
    private String Currency;
    private BigDecimal InvoiceValue;
    private Date InvoiceDate;
    private Date DueDate;
    private Integer Delay;
    private BigDecimal Paid;
    private BigDecimal PaidSO;
}
