/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-11-10 14:18:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-03-12 13:23:04                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/model/TobeTrackingReceiptLine.java                   *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.model;

import com.da.sage.assistant.model.base.ModelTemplate;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

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
