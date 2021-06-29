package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.SupplierDelayHistory;
import sageassistant.model.SupplierDeliveryHistory;
import sageassistant.model.SupplierDetails;
import sageassistant.model.SupplierName;
import sageassistant.model.SupplierOpenItems;
import sageassistant.model.SupplierSummaryAmount;
import sageassistant.model.SupplierSummaryQty;


@Mapper
public interface SupplierMapper {
	List<SupplierName> supplierByCodeOrName(@Param("SupplierCodeOrName") String SupplierCodeOrName);
	List<SupplierDetails> supplierDetailsByCode(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryAmount> supplierTotalAmount(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryQty> supplierTotalProjectQty(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryQty> supplierTotalItemQty(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryQty> supplierTotalProductQty(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryAmount> supplierOpenAmount(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryQty> supplierOpenProjectQty(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryQty> supplierOpenItemQty(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryQty> supplierOpenProductQty(@Param("SupplierCode") String SupplierCode);
	List<SupplierOpenItems> supplierOpenItems(@Param("SupplierCode") String SupplierCode);
	List<SupplierDeliveryHistory> supplierDeliveryHistory(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierDelayHistory> supplierDelayHistory(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	String purchaseDate(@Param("PurchaseNO") String PurchaseNO);
}
