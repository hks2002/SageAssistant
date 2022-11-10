/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-11-10 14:18:38
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-11-10 14:40:28
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeTrackingReceiptLine.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TobeTrackingReceiptLine extends ModelTemplate {

    private static final long serialVersionUID = 1L;

    private String ReceiptNO;
    private String ReceiptLine;
    private String ReceiptPurchaseNO;
    private String ReceiptPurchaseLine;
    private Date ReceiptDate;
    private String Receiptor;
    private Integer ReceiptQty;
}
