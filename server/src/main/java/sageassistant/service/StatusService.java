package sageassistant.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.StatusMapper;
import sageassistant.dao2.TrackingMapper;
import sageassistant.model.TobeClosedWO;
import sageassistant.model.TobeDealWithOrderLine;
import sageassistant.model.TobeDelivery;
import sageassistant.model.TobePurchaseBom;
import sageassistant.model.TobeReceive;
import sageassistant.model.TrackingNotes;
import sageassistant.model.TobeTracking;
import sageassistant.utils.Utils;

@Service
public class StatusService {
	private static final Logger log = LogManager.getLogger();

	@Autowired
	private StatusMapper statusMapper;

	@Autowired
	private TrackingMapper trackingMapper;

	@Autowired
	private CurrencyService currencyService;

	public List<TobeDelivery> findTobeDeliveryBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeDelivery> listPage = statusMapper.findTobeDeliveryBySite(site);

		for (TobeDelivery o : listPage) {
			// log.debug("ooo:" + o.toString());
			String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
			// log.debug("key:" + key);
			try {
				o.setRate(Float.parseFloat(currencyService.cache.get(key)));
				// log.debug("Rate:" + o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			// o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
		}

		return listPage;
	}

	public List<TobeReceive> findTobeReceiveBySite(String site, Integer count) {
		PageHelper.startPage(1, count);
		List<TobeReceive> listPage = statusMapper.findTobeReceiveBySite(site);

		for (TobeReceive o : listPage) {
			String key = o.getCurrency() + "USD" + Utils.formatDate(o.getOrderDate());
			// log.debug("key:" + key);
			try {
				o.setRate(Float.parseFloat(currencyService.cache.get(key)));
				// log.debug("Rate:" + o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setUSD(o.getNetPrice().multiply(new BigDecimal(o.getRate())));
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

	public List<TobeTracking> findTobeTracking(String site) {
		List<TobeTracking> listPage = statusMapper.findTobeTracking(site);

		return listPage;
	}

	public List<TrackingNotes> findTrackingNotes(String trackCode) {
		List<TrackingNotes> listPage = trackingMapper.findTrackingNotes(trackCode);

		return listPage;
	}

	public Boolean insertTrackingNotes(String trackCode, String note, String createBy) {
		Date date = new Date(System.currentTimeMillis());
		Integer rst = trackingMapper.insertTrackingNotes(trackCode, note, date.toString(), createBy);
		if (rst > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean deleteTrackingNotes(String trackCode) {
		Integer rst = trackingMapper.deleteTrackingNotes(trackCode);
		if (rst == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean updateTrackingNotes(String trackCode, String note, String updateBy) {
		Date date = new Date(System.currentTimeMillis());
		List<TrackingNotes> notes = findTrackingNotes(trackCode);
		log.info(notes.toString());
		if (notes.size() > 0) {
			Integer rst = trackingMapper.updateTrackingNotes(trackCode, note, date.toString(), updateBy);
			if (rst == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return insertTrackingNotes(trackCode, note, updateBy);
		}
	}

}
