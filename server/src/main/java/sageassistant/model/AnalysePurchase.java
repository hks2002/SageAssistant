/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-09-21 09:17:04
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-23 09:24:32
 * @FilePath       : \server\src\main\java\sageassistant\model\AnalysePurchase.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class AnalysePurchase extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String ProjectNO;
    private String PurchaseNO;
    private Double NetPrice;
    private Date ProjectDate;
    private Date PurchaseDate;
    private Integer ROWNUM;
}
