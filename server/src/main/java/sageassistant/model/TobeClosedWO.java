/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:30
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:04:03
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeClosedWO.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TobeClosedWO extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String ProjectNO;
    private String OrderNO;
    private String WorkOrderNO;
    private String WOStatus;
    private String ProductionStatus;
    private String OrderType;
    private String CustomerCode;
    private String CustomerName;
    private String PN;
    private Integer Qty;
    private Date OrderDate;
}
