package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.SupplyDelayHistory;
import sageassistant.model.SupplyDeliveryHistory;
import sageassistant.model.SupplyDetails;
import sageassistant.model.SupplyName;
import sageassistant.model.SupplyOpenItems;
import sageassistant.model.SupplySummaryAmount;
import sageassistant.model.SupplySummaryQty;


@Mapper
public interface SupplyMapper {
	List<SupplyName> supplyByCodeOrName(@Param("SupplyCodeOrName") String SupplyCodeOrName);
	SupplyDetails supplyDetailsByCode(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryAmount> supplyTotalAmount(@Param("SupplyCode") String SupplyCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplySummaryQty> supplyTotalProjectQty(@Param("SupplyCode") String SupplyCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplySummaryQty> supplyTotalItemQty(@Param("SupplyCode") String SupplyCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplySummaryQty> supplyTotalProductQty(@Param("SupplyCode") String SupplyCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplySummaryAmount> supplyOpenAmount(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyOpenProjectQty(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyOpenItemQty(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyOpenProductQty(@Param("SupplyCode") String SupplyCode);
	List<SupplyOpenItems> supplyOpenItems(@Param("SupplyCode") String SupplyCode);
	List<SupplyDeliveryHistory> supplyDeliveryHistory(@Param("SupplyCode") String SupplyCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
	List<SupplyDelayHistory> supplyDelayHistory(@Param("SupplyCode") String SupplyCode, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
}
