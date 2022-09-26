/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:03:22
 * @FilePath       : \server\src\main\java\sageassistant\model\StockSummary.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class StockSummary extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String G;
    private String A;
    private String Location;
    private String PN;
    private String Description;
    private String OptionPN;
    private Float Qty;
    private BigDecimal Cost;
}
