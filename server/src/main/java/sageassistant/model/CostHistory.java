/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:58:53
 * @FilePath       : \server\src\main\java\sageassistant\model\CostHistory.java
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
