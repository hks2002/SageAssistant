/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:02:37
 * @FilePath       : \server\src\main\java\sageassistant\model\SalesHistory.java
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
public class SalesHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String SalesSite;
    private String OrderNO;
    private String PN;
    private BigDecimal NetPrice;
    private String Currency;
    private Integer Qty;
    private String CustomerName;
    private String CustomerCode;
    private Date OrderDate;
    private BigDecimal USD;
    private Float Rate;
    private String TradeTerm;
}
