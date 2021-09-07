package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.StockHistory;
import sageassistant.model.StockSummary;


@Mapper
public interface StockMapper {
	String checkPN(@Param("pnRoot") String pnRoot);
	String findStockOptionPN(@Param("pnRoot") String pnRoot);
	Float findStockQty(@Param("Site") String Site, @Param("pnRoot") String pnRoot);
	List<StockSummary> findStockSummaryBySite(@Param("Site") String Site);
	List<StockHistory> findStockHistoryBySite(@Param("Site") String Site, @Param("PnOrName") String PnOrName, @Param("DateFrom") String DateFrom, @Param("DateTo") String DateTo);
}
