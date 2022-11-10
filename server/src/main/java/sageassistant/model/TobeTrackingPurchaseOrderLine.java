/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-11-10 14:18:43
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-11-10 14:38:54
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeTrackingPurchaseOrderLine.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TobeTrackingPurchaseOrderLine extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String PurchaseNO;
    private Integer PurchaseLine;
    private String PurchaseProjectNO;
    private String PurchasePN;
    private String PurchasePNDesc;
    private Integer PurchaseQTY;
    private String PurchaseUnit;
    private String VendorCode;
    private String VendorName;
    private Date PurchaseAckDate;
    private Date PurchaseExpectDate;
    private String PurchaseComment;
    private Date PurchaseDate;
    private String PurchaseUser;
}
