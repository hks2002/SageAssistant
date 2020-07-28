package sageassistant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.StatusMapper;
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

		// PageHelper override toString, added page info, here output clean list
		List<TobeDelivery> listOri = new ArrayList<>();

		for (TobeDelivery o : listPage) {
			String key=o.getCurrency()+"RMB"+Utils.formatDate(o.getOrderDate());
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
	
	public List<TobeReceive> findTobeReceiveBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeReceive> listPage = statusMapper.findTobeReceiveBySite(site);

		// PageHelper override toString, added page info, here output clean list
		List<TobeReceive> listOri = new ArrayList<>();

		for (TobeReceive o : listPage) {
			String key=o.getCurrency()+"RMB"+Utils.formatDate(o.getOrderDate());
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
	
	public List<TobeDealWithOrderLine> findTobeDealWithOrderLineBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeDealWithOrderLine> listPage = statusMapper.findTobeDealWithOrderLineBySite(site);

		// PageHelper override toString, added page info, here output clean list
		List<TobeDealWithOrderLine> listOri = new ArrayList<>();

		for (TobeDealWithOrderLine o : listPage) {
			listOri.add(o);
		}

		return listOri;
	}
	
	public List<TobePurchaseBom> findTobePurchaseBomBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobePurchaseBom> listPage = statusMapper.findTobePurchaseBomBySite(site);

		// PageHelper override toString, added page info, here output clean list
		List<TobePurchaseBom> listOri = new ArrayList<>();

		for (TobePurchaseBom o : listPage) {
			listOri.add(o);
		}

		return listOri;
	}

}
