package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.RptCOC;
import sageassistant.model.RptCOCLot;
import sageassistant.model.RptCOCSerial;
import sageassistant.model.RptDelivery;
import sageassistant.model.RptInvoice;
import sageassistant.model.RptInvoice2;
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

	List<RptCOCSerial> findCOCSerialByProjectNOAndPn(@Param("ProjectNO") String ProjectNO, @Param("PN") String PN);

	List<RptCOCLot> findCOCLotByProjectNOAndPn(@Param("ProjectNO") String ProjectNO, @Param("PN") String PN);

	List<RptPurchase> findPurchaseByPurchaseNO(@Param("PurchaseNO") String PurchaseNO);

	List<RptPurchaseTax> findPurchaseTaxByPurchaseNO(@Param("PurchaseNO") String PurchaseNO);

	List<RptWorkOrder> findWorkOrderByProjectOrWorkOrderNO(@Param("ProjectOrWorkOrderNO") String ProjectOrWorkOrderNO);

	List<RptWorkOrderComp> findWorkOrderCompByMfgNO(@Param("MfgNO") String MfgNO);

	List<RptWorkOrderOpestt> findWorkOrderOpesttByMfgNO(@Param("MfgNO") String MfgNO);

	List<RptWorkOrderSfichsui> findWorkOrderSfichsuiByMfgNO(@Param("MfgNO") String MfgNO);

	List<RptInvoice> findInvoiceByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);

	List<RptInvoicePay> findInvoicePayByInvoiceNO(@Param("InvoiceNO") String InvoiceNO);
	
	List<RptInvoice2> findInvoice2ByOrderNO(@Param("OrderNO") String OrderNO);

	List<RptInvoicePay> findInvoice2PayByOrderNO(@Param("OrderNO") String OrderNO);
	
	List<RptDelivery> findDeliveryByDeliveryNO(@Param("DeliveryNo") String DeliveryNo);

	List<RptReceipt> findReceiptByReceiptNO(@Param("ReceiptNO") String ReceiptNO);
	
	List<RptReceipt> findReceiptByPurchaseSiteVenderCodeDuration(@Param("PurchaseSite") String PurchaseSite, @Param("VendorCode") String VendorCode, @Param("StartDay") String StartDay, @Param("EndDay") String EndDay);

	List<RptSOA> findSOABySiteAndBPCode(@Param("Site") String Site, @Param("BPCode") String BPCode);
}
