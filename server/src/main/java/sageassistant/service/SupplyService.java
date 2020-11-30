package sageassistant.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import sageassistant.dao.SupplyMapper;
import sageassistant.model.SupplyDelayHistory;
import sageassistant.model.SupplyDeliveryHistory;
import sageassistant.model.SupplyDetails;
import sageassistant.model.SupplyName;
import sageassistant.model.SupplyOpenItems;
import sageassistant.model.SupplySummaryAmount;
import sageassistant.model.SupplySummaryQty;
import sageassistant.utils.Utils;

@Service
public class SupplyService {
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private SupplyMapper supplyMapper;

	public List<SupplyName> supplyByCodeOrName(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<SupplyName> listPage = supplyMapper.supplyByCodeOrName("%" + cond + "%");

		// PageHelper override toString, added page info, here output clean list
		List<SupplyName> listOri = new ArrayList<>();

		for (SupplyName o : listPage) {
			listOri.add(o);
		}

		return listOri;
	}

	public List<SupplySummaryAmount> supplyTotalAmount(String SupplyCode, String DateFrom, String DateTo) {
		List<SupplySummaryAmount> listPage = supplyMapper.supplyTotalAmount(SupplyCode, DateFrom, DateTo);

		// PageHelper override toString, added page info, here output clean list
		List<SupplySummaryAmount> listOri = new ArrayList<>();

		for (SupplySummaryAmount o : listPage) {
			String key=o.getCurrency()+"USD"+Utils.formatDate(new Date());
			log.debug("key:"+key);
			try {
				o.setRate(Float.parseFloat(CurrencyService.cache.get(key)));
				log.debug("Rate:"+o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setUSD(o.getAmount()*o.getRate());
			listOri.add(o);
		}

		return listOri;
	}

	public List<SupplySummaryQty> supplyTotalProjectQty(@Param("SupplyCode") String SupplyCode, String DateFrom, String DateTo) {
		return supplyMapper.supplyTotalProjectQty(SupplyCode, DateFrom, DateTo);
	}
	
	public List<SupplySummaryQty> supplyTotalItemQty(@Param("SupplyCode") String SupplyCode, String DateFrom, String DateTo) {
		return supplyMapper.supplyTotalItemQty(SupplyCode, DateFrom, DateTo);
	}
	
	public List<SupplySummaryQty> supplyTotalProductQty(@Param("SupplyCode") String SupplyCode, String DateFrom, String DateTo) {
		return supplyMapper.supplyTotalProductQty(SupplyCode, DateFrom, DateTo);
	}
	
	public List<SupplySummaryQty> supplyTotalQty(@Param("SupplyCode") String SupplyCode, String DateFrom, String DateTo) {
		List<SupplySummaryQty> listProject = supplyMapper.supplyTotalProjectQty(SupplyCode, DateFrom, DateTo);
		List<SupplySummaryQty> listProduct = supplyMapper.supplyTotalProductQty(SupplyCode, DateFrom, DateTo);
		List<SupplySummaryQty> listItem = supplyMapper.supplyTotalItemQty(SupplyCode, DateFrom, DateTo);

		List<SupplySummaryQty> listAll = new ArrayList<>();

		for (SupplySummaryQty o : listProject) {
			listAll.add(o);
		}
		for (SupplySummaryQty o : listProduct) {
			listAll.add(o);
		}for (SupplySummaryQty o : listItem) {
			listAll.add(o);
		}
		return listAll;
	}
	
	public List<SupplySummaryAmount> supplyOpenAmount(String SupplyCode) {
		List<SupplySummaryAmount> listPage = supplyMapper.supplyOpenAmount(SupplyCode);

		// PageHelper override toString, added page info, here output clean list
		List<SupplySummaryAmount> listOri = new ArrayList<>();

		for (SupplySummaryAmount o : listPage) {
			String key=o.getCurrency()+"USD"+Utils.formatDate(new Date());
			log.debug("key:"+key);
			try {
				o.setRate(Float.parseFloat(CurrencyService.cache.get(key)));
				log.debug("Rate:"+o.getRate());
			} catch (NumberFormatException e) {
				log.error(e.getMessage());
			} catch (ExecutionException e) {
				log.error(e.getMessage());
			}
			o.setUSD(o.getAmount()*o.getRate());
			listOri.add(o);
		}

		return listOri;
	}
	
	public List<SupplySummaryQty> supplyOpenProjectQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyOpenProjectQty(SupplyCode);
	}
	
	public List<SupplySummaryQty> supplyOpenItemQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyOpenItemQty(SupplyCode);
	}
	
	public List<SupplySummaryQty> supplyOpenProductQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyOpenProductQty(SupplyCode);
	}
	
	public List<SupplySummaryQty> supplyOpenQty(@Param("SupplyCode") String SupplyCode){
		List<SupplySummaryQty> listProject = supplyMapper.supplyOpenProjectQty(SupplyCode);
		List<SupplySummaryQty> listProduct = supplyMapper.supplyOpenProductQty(SupplyCode);
		List<SupplySummaryQty> listItem = supplyMapper.supplyOpenItemQty(SupplyCode);

		List<SupplySummaryQty> listAll = new ArrayList<>();

		for (SupplySummaryQty o : listProject) {
			listAll.add(o);
		}
		for (SupplySummaryQty o : listProduct) {
			listAll.add(o);
		}for (SupplySummaryQty o : listItem) {
			listAll.add(o);
		}
		return listAll;
	}
	
	public List<SupplyDeliveryHistory> supplyDeliveryHistory(@Param("SupplyCode") String SupplyCode, String DateFrom, String DateTo) {
		return supplyMapper.supplyDeliveryHistory(SupplyCode, DateFrom, DateTo);
	}
	
	public List<SupplyDelayHistory> supplyDelayHistory(@Param("SupplyCode") String SupplyCode, String DateFrom, String DateTo) {
		return supplyMapper.supplyDelayHistory(SupplyCode, DateFrom, DateTo);
	}
	
	public List<SupplyOpenItems> supplyOpenItems(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyOpenItems(SupplyCode);
	}
	
	public SupplyDetails supplyDetails(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyDetailsByCode(SupplyCode);
	}
}
