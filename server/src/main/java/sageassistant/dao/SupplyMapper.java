package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.SupplyDelayHistory;
import sageassistant.model.SupplyDeliveryHistory;
import sageassistant.model.SupplyName;
import sageassistant.model.SupplyOpenLines;
import sageassistant.model.SupplySummaryAmount;
import sageassistant.model.SupplySummaryQty;


@Mapper
public interface SupplyMapper {
	List<SupplyName> supplyByCodeOrName(@Param("SupplyCodeOrName") String SupplyCodeOrName);
	List<SupplySummaryAmount> supplyTotalAmount(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyTotalProjectQty(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyTotalLineQty(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyTotalProductQty(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryAmount> supplyOpenAmount(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyOpenProjectQty(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyOpenLineQty(@Param("SupplyCode") String SupplyCode);
	List<SupplySummaryQty> supplyOpenProductQty(@Param("SupplyCode") String SupplyCode);
	List<SupplyDeliveryHistory> supplyDeliveryHistory(@Param("SupplyCode") String SupplyCode, @Param("YearCount") int YearCount);
	List<SupplyDelayHistory> supplyDelayHistory(@Param("SupplyCode") String SupplyCode, @Param("YearCount") int YearCount);
	List<SupplyOpenLines> supplyOpenLines(@Param("SupplyCode") String SupplyCode);
}
