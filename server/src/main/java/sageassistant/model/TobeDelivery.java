/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:30
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:04:12
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeDelivery.java
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
public class TobeDelivery extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String ProjectNO;
    private String OrderNO;
    private String OrderType;
    private String PN;
    private Integer Qty;
    private String Description;
    private String CustomerCode;
    private String CustomerName;
    private String Currency;
    private BigDecimal NetPrice;
    private BigDecimal USD;
    private Float Rate;
    private Date OrderDate;
    private Date RequestDate;
    private Date PlanedDate;
}
