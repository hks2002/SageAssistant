/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:01:52
 * @FilePath       : \server\src\main\java\sageassistant\model\RptInvoice2Pay.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RptInvoice2Pay {

    private String NUM_0;
    private Date DUDDAT_0;
    private Integer PAMTYP_0;
    private String PAM_0;
    private String CUR_0;
    private BigDecimal AMTCUR_0;
    private BigDecimal PAYCUR_0;
    private BigDecimal TMPCUR_0;
}
