/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-11-10 14:18:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:22:50                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/TobeTrackingBOMLine.java                       *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TobeTrackingBOMLine extends ModelTemplate {

    private static final long serialVersionUID = 1L;
    private String BomProjectNO;
    private String WorkOrderNO;
    private String BomSeq;
    private String BomPN;
    private String BomDesc;
    private Integer BomQTY;
    private String BomUnit;
    private Integer ShortQty;
    private Integer AllQty;
    private String BomRequestDate;
    private String StockPN;
    private Integer AvaQty;
}
