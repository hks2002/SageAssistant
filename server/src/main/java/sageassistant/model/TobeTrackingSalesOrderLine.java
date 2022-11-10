/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-06-09 13:13:20
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-11-10 14:32:42
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeTrackingSalesOrderLine.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TobeTrackingSalesOrderLine extends ModelTemplate {

    private static final long serialVersionUID = 1L;
    private Integer ItemNO;
    private String OrderNO;
    private Integer OrderLine;
    private String TrackingNO;
    private String ProjectNO;
    private String OriProjectNO;
    private String OrderType;
    private String OrderPN;
    private String OrderPNDesc;
    private Integer OrderQTY;
    private Float OrderPrice;
    private String OrderCurrency;
    private String CustomerCode;
    private String CustomerName;
    private Date OrderDate;
    private Date OrderRequestDate;
    private Date OrderPlanedDate;
    private String OrderSADFlag;
    private Date OrderSADDate;
    private String OrderProductFlag;
    private Date OrderProductDate;
    private String OrderDeliveryFlag;
    private String ProjectStatus;
    private String ProjectBlockReason;
    private String ProjectComment;
    private String ProjectAction;
    private Integer DaysLeft;
}
