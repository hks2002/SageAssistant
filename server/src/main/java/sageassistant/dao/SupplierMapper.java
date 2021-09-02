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
	List<SupplierName> findSupplierByCodeOrName(@Param("SupplierCodeOrName") String SupplierCodeOrName);
	List<SupplierDetails> findSupplierDetailsByCode(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryAmount> findSupplierTotalAmount(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryQty> findSupplierTotalProjectQty(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryQty> findSupplierTotalItemQty(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryQty> findSupplierTotalProductQty(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierSummaryAmount> findSupplierOpenAmount(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryQty> findSupplierOpenProjectQty(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryQty> findSupplierOpenItemQty(@Param("SupplierCode") String SupplierCode);
	List<SupplierSummaryQty> findSupplierOpenProductQty(@Param("SupplierCode") String SupplierCode);
	List<SupplierOpenItems> findSupplierOpenItems(@Param("SupplierCode") String SupplierCode);
	List<SupplierDeliveryHistory> findSupplierDeliveryHistory(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplierDelayHistory> findSupplierDelayHistory(@Param("SupplierCode") String SupplierCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	String findPurchaseDate(@Param("PurchaseNO") String PurchaseNO);
}
