/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-09-21 12:36:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-23 10:10:07
 * @FilePath       : \server\src\main\java\sageassistant\model\AnalyseSales.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class AnalyseSales extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String OrderNO;
    private Date OrderDate;
    private String CustomerCode;
    private String CustomerName;
    private Double NetPrice;
    private Integer QTY;
    private Integer ROWNUM;
}
