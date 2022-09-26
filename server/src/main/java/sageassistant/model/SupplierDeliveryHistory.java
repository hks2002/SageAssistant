/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:01:29
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:03:32
 * @FilePath       : \server\src\main\java\sageassistant\model\SupplierDeliveryHistory.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class SupplierDeliveryHistory extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String Site;
    private String SupplierCode;
    private String PurchaseNO;
    private String ProjectNO;
    private String PN;
    private String Description;
    private Date AckDate;
    private Date ExpectDate;
    private Date OrderDate;
    private String ReceiptNO;
    private Date ReceiptDate;
    private int DaysNeed;
}
