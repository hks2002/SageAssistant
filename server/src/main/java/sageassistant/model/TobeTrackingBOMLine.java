/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-11-10 14:18:32
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-11-10 14:37:37
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeTrackingBOMLine.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

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
