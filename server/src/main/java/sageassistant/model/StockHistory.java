/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:02:46
 * @FilePath       : \server\src\main\java\sageassistant\model\StockHistory.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class StockHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Location;
    private String Seq;
    private String PN;
    private String Description;
    private Float Qty;
    private String Unit;
    private BigDecimal Cost;
    private String ProjectNO;
    private String SourceNO;
    private String SourceLine;
    private String EntryNO;
    private String EntryLine;
    private String CreateUser;
    private Date CreateDate;
}
