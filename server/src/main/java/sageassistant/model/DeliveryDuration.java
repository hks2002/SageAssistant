/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 13:59:55
 * @FilePath       : \server\src\main\java\sageassistant\model\DeliveryDuration.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class DeliveryDuration extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String SalesSite;
    private String PN;
    private Date OrderDate;
    private Date ShipDate;
    private Integer Duration;
}
