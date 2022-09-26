/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:55:01
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 14:14:38
 * @FilePath       : \server\src\main\java\sageassistant\dao\RptMapper.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sageassistant.model.RptCOC;
import sageassistant.model.RptCOCSerialLot;
import sageassistant.model.RptDelivery;
import sageassistant.model.RptInvoice;
import sageassistant.model.RptInvoice2;
import sageassistant.model.RptInvoicePackage;
import sageassistant.model.RptInvoicePay;
import sageassistant.model.RptPurchase;
import sageassistant.model.RptPurchaseTax;
import sageassistant.model.RptReceipt;
import sageassistant.model.RptSOA;
import sageassistant.model.RptWorkOrder;
import sageassistant.model.RptWorkOrderComp;
import sageassistant.model.RptWorkOrderOpestt;
import sageassistant.model.RptWorkOrderSfichsui;

@Mapper
public interface RptMapper {
    List<RptCOC> findCOCByProjectNO(@Param("ProjectNO") String ProjectNO);

    List<RptCOCSerialLot> findCOCSerialLotByProjectNOAndPn(
        @Param("ProjectNO") String ProjectNO,
        @Param("PN") String PN
    );

    List<RptPurchase> findPurchaseByPurchaseNO(@Param("PurchaseNO") String PurchaseNO);

    List<RptPurchaseTax> findPurchaseTaxByPurchaseNO(@Param("PurchaseNO") String PurchaseNO);

    List<RptWorkOrder> findWorkOrderByProjectOrWorkOrderNO(@Param("ProjectOrWorkOrderNO") String ProjectOrWorkOrderNO);

    List<RptWorkOrderComp> findWorkOrderCompByMfgNO(@Param("MfgNO") String MfgNO);

    List<RptWorkOrderOpestt> findWorkOrderOpesttByMfgNO(@Param("MfgNO") String MfgNO);

    List<RptWorkOrderSfichsui> findWorkOrderSfichsuiByMfgNO(@Param("MfgNO") String MfgNO);

    List<RptInvoice> findInvoiceByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

    List<RptInvoicePackage> findInvoicePackageByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

    List<RptInvoicePay> findInvoicePayByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

    List<RptInvoice2> findInvoice2ByOrderNO(@Param("OrderNO") String OrderNO);

    List<RptInvoicePay> findInvoice2PayByOrderNO(@Param("OrderNO") String OrderNO);

    List<RptDelivery> findDeliveryByDeliveryNO(@Param("DeliveryNo") String DeliveryNo);

    List<RptReceipt> findReceiptByReceiptNO(@Param("ReceiptNO") String ReceiptNO);

    List<RptReceipt> findReceiptByPurchaseSiteVenderCodeDuration(
        @Param("PurchaseSite") String PurchaseSite,
        @Param("VendorCode") String VendorCode,
        @Param("StartDay") String StartDay,
        @Param("EndDay") String EndDay
    );

    List<RptSOA> findSOABySiteAndBPCode(@Param("Site") String Site, @Param("BPCode") String BPCode);
}
