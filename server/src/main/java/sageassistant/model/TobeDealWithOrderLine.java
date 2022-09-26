/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:30
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:04:08
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeDealWithOrderLine.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TobeDealWithOrderLine extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String SalesOrderNO;
    private String ProjectNO;
    private String OrderType;
    private String OrderCategory;
    private String PN;
    private Integer Qty;
    private String Unit;
    private String Description;
    private String CustomerCode;
    private String CustomerName;
    private Date OrderDate;
    private Date DemandDate;
}
