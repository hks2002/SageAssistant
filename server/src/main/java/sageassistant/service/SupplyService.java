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
import sageassistant.model.SupplyName;
import sageassistant.model.SupplyOpenLines;
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

	public List<SupplySummaryAmount> supplyTotalAmount(String SupplyCode) {
		List<SupplySummaryAmount> listPage = supplyMapper.supplyTotalAmount(SupplyCode);

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

	public List<SupplySummaryQty> supplyTotalProjectQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyTotalProjectQty(SupplyCode);
	}
	
	public List<SupplySummaryQty> supplyTotalLineQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyTotalLineQty(SupplyCode);
	}
	
	public List<SupplySummaryQty> supplyTotalProductQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyTotalProductQty(SupplyCode);
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
	
	public List<SupplySummaryQty> supplyOpenLineQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyOpenLineQty(SupplyCode);
	}
	
	public List<SupplySummaryQty> supplyOpenProductQty(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyOpenProductQty(SupplyCode);
	}
	
	public List<SupplyDeliveryHistory> supplyDeliveryHistory(@Param("SupplyCode") String SupplyCode, @Param("YearCount") int YearCount){
		return supplyMapper.supplyDeliveryHistory(SupplyCode, -YearCount);
	}
	
	public List<SupplyDelayHistory> supplyDelayHistory(@Param("SupplyCode") String SupplyCode, @Param("YearCount") int YearCount){
		return supplyMapper.supplyDelayHistory(SupplyCode, -YearCount);
	}
	
	public List<SupplyOpenLines> supplyOpenLines(@Param("SupplyCode") String SupplyCode){
		return supplyMapper.supplyOpenLines(SupplyCode);
	}
}
