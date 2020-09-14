package sageassistant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sageassistant.model.CostHistory;
import sageassistant.model.DeliveryDuration;
import sageassistant.model.PnDetails;
import sageassistant.model.PnRootPn;
import sageassistant.model.PnStatus;
import sageassistant.model.QuoteHistory;
import sageassistant.model.SalesHistory;
import sageassistant.model.StockInfo;

@Mapper
public interface PnMapper {		
	List<PnRootPn> findPnByLike(@Param("pnOrPnRoot") String pnRootOrPn);
	List<PnDetails> findAllPnByPnRoot(@Param("pnRoot") String pnRoot);
	List<SalesHistory> findSalesHistoryByPnRoot(@Param("pnRoot") String pnRoot);
	List<QuoteHistory> findQuoteHistoryByPnRoot(@Param("pnRoot") String pnRoot);
	List<CostHistory> findCostHistoryByPnRoot(@Param("pnRoot") String pnRoot);
	List<DeliveryDuration> findDeliveryDurationByPnRoot(@Param("pnRoot") String pnRoot);
	List<StockInfo> findStockInfoByPnRoot(@Param("pnRoot") String pnRoot);	
	List<PnStatus> findObseletPnBySite(@Param("site") String site);
}
