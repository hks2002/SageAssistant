/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-31 16:12:00
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:59:25
 * @FilePath       : \server\src\main\java\sageassistant\model\CustomerDeliveryHistory.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class CustomerDeliveryHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Site;
    private String CustomerCode;
    private String OrderNO;
    private String ProjectNO;
    private String PN;
    private String Description;
    private Date OrderDate;
    private Date DemandDate;
    private Date ShipDate;
    private String DeliveryNO;
    private int DaysNeed;
}
