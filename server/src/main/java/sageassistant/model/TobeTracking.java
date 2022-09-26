/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-06-09 13:13:20
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:04:39
 * @FilePath       : \server\src\main\java\sageassistant\model\TobeTracking.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import sageassistant.model.base.ModelTemplate;

@Getter
@Setter
public class TobeTracking extends ModelTemplate {

    private static final long serialVersionUID = 1L;
    private Integer ItemNO;
    private String OrderNO;
    private Integer OrderLine;
    private String TrackingNO;
    private String OrderProjectNO;
    private String OrderType;
    private String OrderPN;
    private String OrderPNDesc;
    private Integer OrderQTY;
    private Float OrderPrice;
    private String OrderCurrency;
    private String CustomerCode;
    private String CustomerName;
    private Date OrderDate;
    private Date OrderRequestDate;
    private Date OrderPlanedDate;
    private String OrderSADFlag;
    private Date OrderSADDate;
    private String OrderProductFlag;
    private Date OrderProductDate;
    private String OrderDeliveryFlag;
    private String ProjectStatus;
    private String ProjectBlockReason;
    private String ProjectComment;
    private String ProjectAction;
    private Integer DaysLeft;
    private String BomProjectNO;
    private String WorkOrderNO;
    private String BomSeq;
    private String BomPN;
    private String BomDesc;
    private Integer BomQTY;
    private String BomUnit;
    private Integer ShortQty;
    private Integer AllQty;
    private String BomRequestDate;
    private String StockPN;
    private Integer AvaQty;
    private String PurchaseNO;
    private Integer PurchaseLine;
    private String PurchaseProjectNO;
    private String PurchasePN;
    private String PurchasePNDesc;
    private Integer PurchaseQTY;
    private String PurchaseUnit;
    private String VendorCode;
    private String VendorName;
    private Date PurchaseAckDate;
    private Date PurchaseExpectDate;
    private String PurchaseComment;
    private Date PurchaseDate;
    private String PurchaseUser;
    private String ReceiptNO;
    private String ReceiptLine;
    private String ReceiptPurchaseNO;
    private String ReceiptPurchaseLine;
    private Date ReceiptDate;
    private String Receipter;
    private Integer ReceiptQty;
    private String ClaimProjectNO;
    private String ClaimNO;
    private String ClaimNote;
    private String ClaimNC0Cri;
    private String ClaimNC0Type;
    private Date ClaimNC0Date;
    private String ClaimNC0Desc;
    private String ClaimNC1Cri;
    private String ClaimNC1Type;
    private Date ClaimNC1Date;
    private String ClaimNC1Desc;
    private String ClaimNC2Cri;
    private String ClaimNC2Type;
    private Date ClaimNC2Date;
    private String ClaimNC2Desc;
    private String ClaimNC3Cri;
    private String ClaimNC3Type;
    private Date ClaimNC3Date;
    private String ClaimNC3Desc;
    private String ClaimNC4Cri;
    private String ClaimNC4Type;
    private Date ClaimNC4Date;
    private String ClaimNC4Desc;
    private String ClaimNC5Cri;
    private String ClaimNC5Type;
    private Date ClaimNC5Date;
    private String ClaimNC5Desc;
    private String ClaimNC6Cri;
    private String ClaimNC6Type;
    private Date ClaimNC6Date;
    private String ClaimNC6Desc;
    private String ClaimNC7Cri;
    private String ClaimNC7Type;
    private Date ClaimNC7Date;
    private String ClaimNC7Desc;
    private String ClaimNC8Cri;
    private String ClaimNC8Type;
    private Date ClaimNC8Date;
    private String ClaimNC8Desc;
    private String ClaimNC9Cri;
    private String ClaimNC9Type;
    private Date ClaimNC9Date;
    private String ClaimNC9Desc;
    private String ClaimNC10Cri;
    private String ClaimNC10Type;
    private Date ClaimNC10Date;
    private String ClaimNC10Desc;
    private String ClaimNC11Cri;
    private String ClaimNC11Type;
    private Date ClaimNC11Date;
    private String ClaimNC11Desc;
}
