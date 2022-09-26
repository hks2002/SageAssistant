/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:02:01
 * @FilePath       : \server\src\main\java\sageassistant\model\RptPurchase.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RptPurchase {

    private String NOMUSR_0;
    private String ADDEML_0;
    private String POHFCY_0;
    private String POHNUM_0;
    private String ORDREF_0;
    private Date ORDDAT_0;
    private String MDL_0;
    private String PTE_0;
    private String BPSNUM_0;
    private String BPRNAM_0;
    private String BPRNAM_1;
    private String BPAADDLIG_0;
    private String BPAADDLIG_1;
    private String BPAADDLIG_2;
    private String POSCOD_0;
    private String CTY_0;
    private String SAT_0;
    private String CRY_0;
    private String CRYNAM_0;
    private String CUR_0;
    private Float TOTORD_0;
    private String TTVORD_0;
    private String BPTNUM_0;
    private String BPTNAM_0;
    private String EECNUM_0;
    private String TEL_0;
    private String FAX_0;
    private String CRY_BUY;
    private String TEL_BUY;
    private String FAX_BUY;
    private Integer POPLIN_0;
    private String ITMREF_0;
    private String ITMDES_0;
    private String ITMDES2_0;
    private String ITMDES3_0;
    private Float DISCRGVAL1_0;
    private String PJT_0;
    private Date EXTRCPDAT_0;
    private BigDecimal LINAMT_0;
    private BigDecimal LINATIAMT_0;
    private Float QTYUOM_0;
    private Float QTYSTU_0;
    private String UOM_0;
    private String STU_0;
    private String YSOQ_BPCSN_0;
    private String YSOQ_PAINT_0;
    private String ITMDESBPS_0;
    private String ITMREFBPS_0;
    private String TEXTE_ENT;
    private String TEXTE_FIN;
    private String TEXTE_LIN;
}
