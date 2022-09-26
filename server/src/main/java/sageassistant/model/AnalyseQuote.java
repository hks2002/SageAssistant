/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-09-21 12:32:40
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-23 10:09:57
 * @FilePath       : \server\src\main\java\sageassistant\model\AnalyseQuote.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class AnalyseQuote extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String QuoteNO;
    private Date QuoteDate;
    private String CustomerCode;
    private String CustomerName;
    private Double NetPrice;
    private String OrderNO;
    private Integer OrderFlag;
    private Integer QTY;
    private Integer ROWNUM;
}
