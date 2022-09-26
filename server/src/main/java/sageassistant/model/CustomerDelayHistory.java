/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-31 16:08:58
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:59:19
 * @FilePath       : \server\src\main\java\sageassistant\model\CustomerDelayHistory.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class CustomerDelayHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Site;
    private String CustomerCode;
    private String OrderNO;
    private String ProjectNO;
    private String PN;
    private String Description;
    private Date AckDate;
    private Date DemandDate;
    private Date OrderDate;
    private String DeliveryNO;
    private Date ShipDate;
    private int DaysDelay;
}
