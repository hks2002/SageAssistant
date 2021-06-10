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

import sageassistant.dao.SupplierMapper;
import sageassistant.model.SupplierDelayHistory;
import sageassistant.model.SupplierDeliveryHistory;
import sageassistant.model.SupplierDetails;
import sageassistant.model.SupplierName;
import sageassistant.model.SupplierOpenItems;
import sageassistant.model.SupplierSummaryAmount;
import sageassistant.model.SupplierSummaryQty;
import sageassistant.utils.Utils;

@Service
public class SupplierService {
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private SupplierMapper supplierMapper;

	public List<SupplierName> supplierByCodeOrName(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<SupplierName> listPage = supplierMapper.supplierByCodeOrName("%" + cond + "%");

		return listPage;
	}

	public List<SupplierSummaryAmount> supplierTotalAmount(String SupplierCode, String DateFrom, String DateTo) {
		List<SupplierSummaryAmount> listPage = supplierMapper.supplierTotalAmount(SupplierCode, DateFrom, DateTo);

		for (SupplierSummaryAmount o : listPage) {
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
		}

		return listPage;
	}

	public List<SupplierSummaryQty> supplierTotalProjectQty(@Param("SupplierCode") String SupplierCode, String DateFrom, String DateTo) {
		return supplierMapper.supplierTotalProjectQty(SupplierCode, DateFrom, DateTo);
	}
	
	public List<SupplierSummaryQty> supplierTotalItemQty(@Param("SupplierCode") String SupplierCode, String DateFrom, String DateTo) {
		return supplierMapper.supplierTotalItemQty(SupplierCode, DateFrom, DateTo);
	}
	
	public List<SupplierSummaryQty> supplierTotalProductQty(@Param("SupplierCode") String SupplierCode, String DateFrom, String DateTo) {
		return supplierMapper.supplierTotalProductQty(SupplierCode, DateFrom, DateTo);
	}
	
	public List<SupplierSummaryQty> supplierTotalQty(@Param("SupplierCode") String SupplierCode, String DateFrom, String DateTo) {
		List<SupplierSummaryQty> listProject = supplierMapper.supplierTotalProjectQty(SupplierCode, DateFrom, DateTo);
		List<SupplierSummaryQty> listProduct = supplierMapper.supplierTotalProductQty(SupplierCode, DateFrom, DateTo);
		List<SupplierSummaryQty> listItem = supplierMapper.supplierTotalItemQty(SupplierCode, DateFrom, DateTo);

		List<SupplierSummaryQty> listAll = new ArrayList<>();

		for (SupplierSummaryQty o : listProject) {
			listAll.add(o);
		}
		for (SupplierSummaryQty o : listProduct) {
			listAll.add(o);
		}for (SupplierSummaryQty o : listItem) {
			listAll.add(o);
		}
		return listAll;
	}
	
	public List<SupplierSummaryAmount> supplierOpenAmount(String SupplierCode) {
		List<SupplierSummaryAmount> listPage = supplierMapper.supplierOpenAmount(SupplierCode);

		for (SupplierSummaryAmount o : listPage) {
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
		}

		return listPage;
	}
	
	public List<SupplierSummaryQty> supplierOpenProjectQty(@Param("SupplierCode") String SupplierCode){
		return supplierMapper.supplierOpenProjectQty(SupplierCode);
	}
	
	public List<SupplierSummaryQty> supplierOpenItemQty(@Param("SupplierCode") String SupplierCode){
		return supplierMapper.supplierOpenItemQty(SupplierCode);
	}
	
	public List<SupplierSummaryQty> supplierOpenProductQty(@Param("SupplierCode") String SupplierCode){
		return supplierMapper.supplierOpenProductQty(SupplierCode);
	}
	
	public List<SupplierSummaryQty> supplierOpenQty(@Param("SupplierCode") String SupplierCode){
		List<SupplierSummaryQty> listProject = supplierMapper.supplierOpenProjectQty(SupplierCode);
		List<SupplierSummaryQty> listProduct = supplierMapper.supplierOpenProductQty(SupplierCode);
		List<SupplierSummaryQty> listItem = supplierMapper.supplierOpenItemQty(SupplierCode);

		List<SupplierSummaryQty> listAll = new ArrayList<>();

		for (SupplierSummaryQty o : listProject) {
			listAll.add(o);
		}
		for (SupplierSummaryQty o : listProduct) {
			listAll.add(o);
		}for (SupplierSummaryQty o : listItem) {
			listAll.add(o);
		}
		return listAll;
	}
	
	public List<SupplierDeliveryHistory> supplierDeliveryHistory(@Param("SupplierCode") String SupplierCode, String DateFrom, String DateTo) {
		return supplierMapper.supplierDeliveryHistory(SupplierCode, DateFrom, DateTo);
	}
	
	public List<SupplierDelayHistory> supplierDelayHistory(@Param("SupplierCode") String SupplierCode, String DateFrom, String DateTo) {
		return supplierMapper.supplierDelayHistory(SupplierCode, DateFrom, DateTo);
	}
	
	public List<SupplierOpenItems> supplierOpenItems(@Param("SupplierCode") String SupplierCode){
		return supplierMapper.supplierOpenItems(SupplierCode);
	}
	
	public SupplierDetails supplierDetails(@Param("SupplierCode") String SupplierCode){
		return supplierMapper.supplierDetailsByCode(SupplierCode);
	}
	
	public String purchaseDate(@Param("PurchaseNO") String PurchaseNO){
		return supplierMapper.purchaseDate(PurchaseNO);
	}
	
}
