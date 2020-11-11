package sageassistant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.PnMapper;
import sageassistant.model.CostHistory;
import sageassistant.model.DeliveryDuration;
import sageassistant.model.PnDetails;
import sageassistant.model.PnRootPn;
import sageassistant.model.PnStatus;
import sageassistant.model.QuoteHistory;
import sageassistant.model.SalesHistory;
import sageassistant.model.StockInfo;
import sageassistant.utils.Utils;

@Service
public class PnService {
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private PnMapper pnMapper;

	public List<PnRootPn> findPnByStartWith(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<PnRootPn> listPage = pnMapper.findPnByLike(cond + "%");

		// PageHelper override toString, added page info, here output clean list
		List<PnRootPn> listOri = new ArrayList<>();

		for (PnRootPn o : listPage) {
			listOri.add(o);
		}

		return listOri;
	}

	public List<PnRootPn> findPnByEndWith(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<PnRootPn> listPage = pnMapper.findPnByLike("%" + cond);

		// PageHelper override toString, added page info, here output clean list
		List<PnRootPn> listOri = new ArrayList<>();

		for (PnRootPn o : listPage) {
			listOri.add(o);
		}

		return listOri;
	}

	public List<PnRootPn> findPnByContains(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<PnRootPn> listPage = pnMapper.findPnByLike("%" + cond + "%");

		// PageHelper override toString, added page info, here output clean list
		List<PnRootPn> listOri = new ArrayList<>();

		for (PnRootPn o : listPage) {
			listOri.add(o);
		}

		return listOri;
	}

	public List<PnDetails> findAllPnByPnRoot(String pnRoot) {
		return pnMapper.findAllPnByPnRoot(pnRoot);
	}

	public List<SalesHistory> findSalesHistoryByPnRoot(String pnRoot, Integer count) {
		PageHelper.startPage(1, count);

		List<SalesHistory> listPage = (ArrayList<SalesHistory>)pnMapper.findSalesHistoryByPnRoot(pnRoot);

		// PageHelper override toString, added page info, here output clean list
		List<SalesHistory> listOri = new ArrayList<>();

		for (SalesHistory o : listPage) {
			String key=o.getCurrency()+"USD"+Utils.formatDate(o.getOrderDate());
			log.debug("key:"+key);
			try {
				o.setRate(Float.parseFloat(CurrencyService.cache.get(key)));
				log.debug("Rate:"+o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setRMB(o.getNetPrice()*o.getRate());
			listOri.add(o);
		}

		return listOri;
	}
	
	public List<QuoteHistory> findQuoteHistoryByPnRoot(String pnRoot, Integer count) {
		PageHelper.startPage(1, count);

		List<QuoteHistory> listPage = pnMapper.findQuoteHistoryByPnRoot(pnRoot);

		// PageHelper override toString, added page info, here output clean list
		List<QuoteHistory> listOri = new ArrayList<>();

		for (QuoteHistory o : listPage) {
			String key=o.getCurrency()+"USD"+Utils.formatDate(o.getQuoteDate());
			log.debug("key:"+key);
			try {
				o.setRate(Float.parseFloat(CurrencyService.cache.get(key)));
				log.debug("Rate:"+o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setRMB(o.getNetPrice()*o.getRate());
			listOri.add(o);
		}

		return listOri;
	}
	
	public List<CostHistory> findCostHistoryByPnRoot(String pnRoot, Integer count) {
		PageHelper.startPage(1, count);

		List<CostHistory> listPage = pnMapper.findCostHistoryByPnRoot(pnRoot);

		// PageHelper override toString, added page info, here output clean list
		List<CostHistory> listOri = new ArrayList<>();

		
		for (CostHistory o : listPage) {
			String key=o.getCurrency()+"USD"+Utils.formatDate(o.getOrderDate());
			log.debug("key:"+key);
			try {
				o.setRate(Float.parseFloat(CurrencyService.cache.get(key)));
				log.debug("Rate:"+o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setRMB(o.getNetPrice()*o.getRate());
			listOri.add(o);
		}
        // one project maybe purchase line with different currency
		
		return listOri;
	}
	
	public List<DeliveryDuration> findDeliveryDurationByPnRoot(@Param("pnRoot") String pnRoot){
		return pnMapper.findDeliveryDurationByPnRoot(pnRoot);
	}
	
	public List<StockInfo> findStockInfoByPnRoot(@Param("pnRoot") String pnRoot){
		return pnMapper.findStockInfoByPnRoot(pnRoot);
	}
	
	public List<PnStatus> findObseletPnBySite(@Param("site") String site){
		return pnMapper.findObseletPnBySite(site);
	}
}
