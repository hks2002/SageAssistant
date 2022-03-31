package sageassistant.service;

import java.math.BigDecimal;
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

import sageassistant.dao.CustomerMapper;
import sageassistant.model.CustomerDelayHistory;
import sageassistant.model.CustomerDeliveryHistory;
import sageassistant.model.CustomerDetails;
import sageassistant.model.CustomerName;
import sageassistant.model.CustomerOpenItems;
import sageassistant.model.CustomerSummaryAmount;
import sageassistant.model.CustomerSummaryQty;
import sageassistant.utils.Utils;

@Service
public class CustomerService {
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private CustomerMapper CustomerMapper;

	public List<CustomerName> getCustomerByCodeOrName(String cond, Integer count) {
		PageHelper.startPage(1, count);
		List<CustomerName> listPage = CustomerMapper.findCustomerByCodeOrName("%" + cond + "%");

		return listPage;
	}

	public List<CustomerSummaryAmount> getCustomerTotalAmount(String CustomerCode, String DateFrom, String DateTo) {
		List<CustomerSummaryAmount> listPage = CustomerMapper.findCustomerTotalAmount(CustomerCode, DateFrom, DateTo);

		for (CustomerSummaryAmount o : listPage) {
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
			o.setUSD(o.getAmount().multiply( new BigDecimal(o.getRate())));
		}

		return listPage;
	}

	public List<CustomerSummaryQty> getCustomerTotalProjectQty(@Param("CustomerCode") String CustomerCode, String DateFrom, String DateTo) {
		return CustomerMapper.findCustomerTotalProjectQty(CustomerCode, DateFrom, DateTo);
	}
	
	public List<CustomerSummaryQty> getCustomerTotalItemQty(@Param("CustomerCode") String CustomerCode, String DateFrom, String DateTo) {
		return CustomerMapper.findCustomerTotalItemQty(CustomerCode, DateFrom, DateTo);
	}
	
	public List<CustomerSummaryQty> getCustomerTotalProductQty(@Param("CustomerCode") String CustomerCode, String DateFrom, String DateTo) {
		return CustomerMapper.findCustomerTotalProductQty(CustomerCode, DateFrom, DateTo);
	}
	
	public List<CustomerSummaryQty> getCustomerTotalQty(@Param("CustomerCode") String CustomerCode, String DateFrom, String DateTo) {
		List<CustomerSummaryQty> listProject = CustomerMapper.findCustomerTotalProjectQty(CustomerCode, DateFrom, DateTo);
		List<CustomerSummaryQty> listProduct = CustomerMapper.findCustomerTotalProductQty(CustomerCode, DateFrom, DateTo);
		List<CustomerSummaryQty> listItem = CustomerMapper.findCustomerTotalItemQty(CustomerCode, DateFrom, DateTo);

		List<CustomerSummaryQty> listAll = new ArrayList<>();

		for (CustomerSummaryQty o : listProject) {
			listAll.add(o);
		}
		for (CustomerSummaryQty o : listProduct) {
			listAll.add(o);
		}for (CustomerSummaryQty o : listItem) {
			listAll.add(o);
		}
		return listAll;
	}
	
	public List<CustomerSummaryAmount> getCustomerOpenAmount(String CustomerCode) {
		List<CustomerSummaryAmount> listPage = CustomerMapper.findCustomerOpenAmount(CustomerCode);

		for (CustomerSummaryAmount o : listPage) {
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
			o.setUSD(o.getAmount().multiply( new BigDecimal(o.getRate())));
		}

		return listPage;
	}
	
	public List<CustomerSummaryQty> getCustomerOpenProjectQty(@Param("CustomerCode") String CustomerCode){
		return CustomerMapper.findCustomerOpenProjectQty(CustomerCode);
	}
	
	public List<CustomerSummaryQty> getCustomerOpenItemQty(@Param("CustomerCode") String CustomerCode){
		return CustomerMapper.findCustomerOpenItemQty(CustomerCode);
	}
	
	public List<CustomerSummaryQty> getCustomerOpenProductQty(@Param("CustomerCode") String CustomerCode){
		return CustomerMapper.findCustomerOpenProductQty(CustomerCode);
	}
	
	public List<CustomerSummaryQty> getCustomerOpenQty(@Param("CustomerCode") String CustomerCode){
		List<CustomerSummaryQty> listProject = CustomerMapper.findCustomerOpenProjectQty(CustomerCode);
		List<CustomerSummaryQty> listProduct = CustomerMapper.findCustomerOpenProductQty(CustomerCode);
		List<CustomerSummaryQty> listItem = CustomerMapper.findCustomerOpenItemQty(CustomerCode);

		List<CustomerSummaryQty> listAll = new ArrayList<>();

		for (CustomerSummaryQty o : listProject) {
			listAll.add(o);
		}
		for (CustomerSummaryQty o : listProduct) {
			listAll.add(o);
		}for (CustomerSummaryQty o : listItem) {
			listAll.add(o);
		}
		return listAll;
	}
	
	public List<CustomerDeliveryHistory> getCustomerDeliveryHistory(@Param("CustomerCode") String CustomerCode, String DateFrom, String DateTo) {
		return CustomerMapper.findCustomerDeliveryHistory(CustomerCode, DateFrom, DateTo);
	}
	
	public List<CustomerDelayHistory> getCustomerDelayHistory(@Param("CustomerCode") String CustomerCode, String DateFrom, String DateTo) {
		return CustomerMapper.findCustomerDelayHistory(CustomerCode, DateFrom, DateTo);
	}
	
	public List<CustomerOpenItems> getCustomerOpenItems(@Param("CustomerCode") String CustomerCode){
		return CustomerMapper.findCustomerOpenItems(CustomerCode);
	}
	
	public List<CustomerDetails> getCustomerDetails(@Param("CustomerCode") String CustomerCode){
		return CustomerMapper.findCustomerDetailsByCode(CustomerCode);
	}

}