/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:30
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:04:29
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeReceive.java
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
