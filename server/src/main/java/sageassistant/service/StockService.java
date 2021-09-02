package sageassistant.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sageassistant.dao.StockMapper;
import sageassistant.model.StockHistory;
import sageassistant.model.StockSummary;

@Service
public class StockService {
	// private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private StockMapper stockMapper;

	public List<StockSummary> stockSummary(@Param("Site") String Site) {
		return stockMapper.findStockSummaryBySite(Site);
	}
	
	public List<StockHistory> stockHistory(@Param("Site") String Site, @Param("PnOrName") String PnOrName,  @Param("DateFrom") String DateFrom,  @Param("DateTo") String DateTo) {
		return stockMapper.findStockHistoryBySite(Site, PnOrName, DateFrom, DateTo);
	}
	
	
}
