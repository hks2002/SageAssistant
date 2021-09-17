package sageassistant.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.StatusMapper;
import sageassistant.model.TobeClosedWO;
import sageassistant.model.TobeDealWithOrderLine;
import sageassistant.model.TobeDelivery;
import sageassistant.model.TobePurchaseBom;
import sageassistant.model.TobeReceive;
import sageassistant.utils.Utils;

@Service
public class StatusService {
	private static final Logger log = LogManager.getLogger();

	@Autowired
	private StatusMapper statusMapper;

	public List<TobeDelivery> findTobeDeliveryBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeDelivery> listPage = statusMapper.findTobeDeliveryBySite(site);

		for (TobeDelivery o : listPage) {
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
			o.setUSD(o.getNetPrice().multiply( new BigDecimal(o.getRate())));
		}

		return listPage;
	}
	
	public List<TobeReceive> findTobeReceiveBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeReceive> listPage = statusMapper.findTobeReceiveBySite(site);

		for (TobeReceive o : listPage) {
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
			o.setUSD(o.getNetPrice().multiply( new BigDecimal(o.getRate())));
		}

		return listPage;
	}
	
	public List<TobeDealWithOrderLine> findTobeDealWithOrderLineBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeDealWithOrderLine> listPage = statusMapper.findTobeDealWithOrderLineBySite(site);

		return listPage;
	}
	
	public List<TobePurchaseBom> findTobePurchaseBomBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobePurchaseBom> listPage = statusMapper.findTobePurchaseBomBySite(site);

		return listPage;
	}

	public List<TobeClosedWO> findTobeClosedWOBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeClosedWO> listPage = statusMapper.findTobeClosedWOBySite(site);

		return listPage;
	}
}
