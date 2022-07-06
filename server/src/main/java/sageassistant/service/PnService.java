package sageassistant.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.PnMapper;
import sageassistant.dao.StockMapper;
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

	@Autowired
	private StockMapper stockMapper;

	@Autowired
	private CurrencyService currencyService;

	public List<PnRootPn> findPnByStartWith(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<PnRootPn> listPage = pnMapper.findPnByLike(cond + "%");

		return listPage;
	}

	public List<PnRootPn> findPnByEndWith(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<PnRootPn> listPage = pnMapper.findPnByLike("%" + cond);

		return listPage;
	}

	public List<PnRootPn> findPnByContains(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<PnRootPn> listPage = pnMapper.findPnByLike("%" + cond + "%");

		return listPage;
	}

	public List<PnDetails> findAllPnByPnRoot(String pnRoot) {
		return pnMapper.findAllPnByPnRoot(pnRoot);
	}

	public List<SalesHistory> findSalesHistoryByPnRoot(String pnRoot, Integer count) {
		PageHelper.startPage(1, count);
		List<SalesHistory> listPage = (ArrayList<SalesHistory>) pnMapper.findSalesHistoryByPnRoot(pnRoot);

		for (SalesHistory o : listPage) {
			String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
			log.debug("key:" + key);
			try {
				o.setRate(Float.parseFloat(currencyService.cache.get(key)));
				log.debug("Rate:" + o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
		}

		return listPage;
	}

	public List<QuoteHistory> findQuoteHistoryByPnRoot(String pnRoot, Integer count) {
		PageHelper.startPage(1, count);
		List<QuoteHistory> listPage = pnMapper.findQuoteHistoryByPnRoot(pnRoot);

		for (QuoteHistory o : listPage) {
			String key = o.getCurrency() + "USD" + Utils.formatDate(o.getQuoteDate());
			log.debug("key:" + key);
			try {
				o.setRate(Float.parseFloat(currencyService.cache.get(key)));
				log.debug("Rate:" + o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
		}

		return listPage;
	}

	public List<CostHistory> findCostHistoryByPnRoot(String pnRoot, Integer count) {
		PageHelper.startPage(1, count);
		List<CostHistory> listPage = pnMapper.findCostHistoryByPnRoot(pnRoot);

		for (CostHistory o : listPage) {
			String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
			log.debug("key:" + key);
			try {
				o.setRate(Float.parseFloat(currencyService.cache.get(key)));
				log.debug("Rate:" + o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
		}
		// one project maybe purchase line with different currency

		return listPage;
	}

	public List<DeliveryDuration> findDeliveryDurationByPnRoot(String pnRoot) {
		return pnMapper.findDeliveryDurationByPnRoot(pnRoot);
	}

	public List<StockInfo> findStockInfoByPnRoot(String pnRoot) {
		return stockMapper.findStockInfoByPnRoot(pnRoot);
	}

	public List<PnStatus> findObseletPnBySite(String site) {
		return pnMapper.findObseletPnBySite(site);
	}
}
