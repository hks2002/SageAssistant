/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:02:13
 * @FilePath       : \server\src\main\java\sageassistant\model\RptWorkOrder.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RptWorkOrder {

    private String MFGNUM_0;
    private String ITMREF_0;
    private String ITMDES1_0;
    private String ITMDES2_0;
    private String ITMDES3_0;
    private String ECCVALMAJ_0;
    private Byte SERMGTCOD_0;
    private Byte LOTMGTCOD_0;
    private String MFGFCY_0;
    private String PJT_0;
    private Float UOMEXTQTY_0;
    private String UOM_0;
    private Date STRDAT_0;
    private Date ENDDAT_0;
    private Date DEMDLVDAT_0;
    private String FCYNAM_0;
    private String CUSORDREF_0;
    private Date ORDDAT_0;
    private String BPCORD_0;
    private String BPCNAM_0;
}
