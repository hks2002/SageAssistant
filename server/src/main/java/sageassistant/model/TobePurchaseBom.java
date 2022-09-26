/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:30
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:04:17
 * @FilePath       : \server\src\main\java\sageassistant\model\TobePurchaseBom.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TobePurchaseBom extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String ProjectNO;
    private String OrderType;
    private String WorkOrderNO;
    private String BomSeq;
    private String CustomerCode;
    private String CustomerName;
    private String ForPN;
    private String PN;
    private String Description;
    private Integer Qty;
    private Integer ShortQty;
    private Integer AllQty;
    private String Unit;
    private Date CreateDate;
    private String CreateUser;
}
