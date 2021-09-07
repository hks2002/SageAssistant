package sageassistant.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
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
	
	public String checkPN(@Param("pnRoot") String pnRoot) {
		return stockMapper.checkPN(pnRoot);
	}
	
	public String findStockOptionPN(@Param("pnRoot") String pnRoot) {
		// remove special character, pnRoot must be url encoded,
		pnRoot=pnRoot.replaceAll(",|\\.| |-|_|/|\\\\", "");
		return stockMapper.findStockOptionPN(pnRoot);
	}
	
	public Float findStockQty(@Param("Site") String Site, @Param("pnRoot") String pnRoot) {
		return stockMapper.findStockQty(Site, pnRoot);
	}
	
	public List<StockSummary> getStockSummary(@Param("Site") String Site) {
		return stockMapper.findStockSummaryBySite(Site);
	}
	
	public List<StockHistory> getStockHistory(@Param("Site") String Site, @Param("PnOrName") String PnOrName,  @Param("DateFrom") String DateFrom,  @Param("DateTo") String DateTo) {
		return stockMapper.findStockHistoryBySite(Site, PnOrName, DateFrom, DateTo);
	}
	
	
}
