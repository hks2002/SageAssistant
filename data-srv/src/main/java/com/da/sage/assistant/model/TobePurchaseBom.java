/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:01:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:22:36                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/TobePurchaseBom.java                           *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

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
