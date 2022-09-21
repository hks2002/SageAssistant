package sageassistant.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sageassistant.dao.AnalyseMapper;
import sageassistant.dao.CommonMapper;
import sageassistant.model.AnalysePurchase;
import sageassistant.model.AnalyseQuote;
import sageassistant.model.AnalyseQuoteSalesCost;
import sageassistant.model.AnalyseSales;

@Service
public class AnalyseService {
	// private static final Logger log = LogManager.getLogger();

	@Autowired
	private AnalyseMapper analyseMapper;

	@Autowired
	private CommonMapper commonMapper;

	public List<AnalyseQuoteSalesCost> analyseQuoteSalesCost(
			@Param("Site") String Site,
			@Param("CategoryCode") String CategoryCode,
			@Param("PnRoot") String PnRoot,
			@Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo,
			@Param("Limit") Integer Limit) {
		return analyseMapper.analyseQuoteSalesCost(Site, CategoryCode, PnRoot, DateFrom, DateTo, Limit);
	}

	public List<AnalyseQuoteSalesCost> analyseQuoteSalesCostAll(
			@Param("CategoryCode") String CategoryCode,
			@Param("PnRoot") String PnRoot,
			@Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo,
			@Param("Limit") Integer Limit) {

		List<AnalyseQuoteSalesCost> listAll = new ArrayList<>();
		for (String Site : commonMapper.getAllSites()) {
			listAll.addAll(analyseMapper.analyseQuoteSalesCost(Site, CategoryCode, PnRoot, DateFrom, DateTo, Limit));
		}
		return listAll;
	}

	public String analyseQuoteSalesCostByTarget(
			@Param("Site") String Site,
			@Param("PnRoot") String PnRoot,
			@Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo,
			@Param("Limit") Integer Limit,
			@Param("Target") String Target) {
		List<AnalyseQuoteSalesCost> result = analyseMapper.analyseQuoteSalesCost(Site, "", PnRoot, DateFrom, DateTo,
				Limit);
		if (result.size() > 0) {
			return result.get(0).toString();
		} else {
			return "{}";
		}
	}

	public String analysePurchase(
			@Param("Site") String Site,
			@Param("PnRoot") String PnRoot,
			@Param("Currency") String Currency,
			@Param("Target") String Target,
			@Param("LastN") String LastN) {

		List<AnalysePurchase> result = analyseMapper.analysePurchase(Site, PnRoot, Currency, LastN);
		String rtn = "";
		HashSet<String> set = new HashSet<String>();

		for (AnalysePurchase o : result) {
			switch (Target) {
				case "ProjectNO":
					set.add(o.getProjectNO());
					break;
				case "PurchaseNO":
					set.add(o.getPurchaseNO());
					break;
				case "ProjectDate":
					set.add(o.getProjectDate().toString());
					break;
				case "PurchaseDate":
					set.add(o.getPurchaseDate().toString());
					break;
				case "NetPrice":
					set.add(o.getNetPrice().toPlainString());
					break;
				default:
					break;
			}
		}

		for (String s : set) {
			rtn += s + ";";
		}
		if (set.size() > 0) {
			rtn = rtn.substring(0, rtn.lastIndexOf(";"));
		}
		return rtn;
	}

	public String analyseQuote(
			@Param("Site") String Site,
			@Param("PnRoot") String PnRoot,
			@Param("Currency") String Currency,
			@Param("Target") String Target,
			@Param("LastN") String LastN) {

		List<AnalyseQuote> result = analyseMapper.analyseQuote(Site, PnRoot, Currency, LastN);
		String rtn = "";
		HashSet<String> set = new HashSet<String>();

		for (AnalyseQuote o : result) {
			switch (Target) {
				case "QuoteNO":
					set.add(o.getQuoteNO());
					break;
				case "QuoteDate":
					set.add(o.getQuoteDate().toString());
					break;
				case "CustomerCode":
					set.add(o.getCustomerCode());
					break;
				case "CustomerName":
					set.add(o.getCustomerName());
					break;
				case "NetPrice":
					set.add(o.getNetPrice().toPlainString());
					break;
				case "OrderNO":
					set.add(o.getOrderNO());
					break;
				case "OrderFlag":
					set.add(o.getOrderFlag().toString());
					break;
				case "QTY":
					set.add(o.getQTY().toString());
					break;
				default:
					break;
			}
		}

		for (String s : set) {
			rtn += s + ";";
		}
		if (set.size() > 0) {
			rtn = rtn.substring(0, rtn.lastIndexOf(";"));
		}
		return rtn;
	}

	public String analyseSales(
			@Param("Site") String Site,
			@Param("PnRoot") String PnRoot,
			@Param("Currency") String Currency,
			@Param("Target") String Target,
			@Param("LastN") String LastN) {

		List<AnalyseSales> result = analyseMapper.analyseSales(Site, PnRoot, Currency, LastN);
		String rtn = "";
		HashSet<String> set = new HashSet<String>();

		for (AnalyseSales o : result) {
			switch (Target) {
				case "OrderNO":
					set.add(o.getOrderNO());
					break;
				case "OrderDate":
					set.add(o.getOrderDate().toString());
					break;
				case "CustomerCode":
					set.add(o.getCustomerCode());
					break;
				case "CustomerName":
					set.add(o.getCustomerName());
					break;
				case "NetPrice":
					set.add(o.getNetPrice().toPlainString());
					break;
				case "QTY":
					set.add(o.getQTY().toString());
					break;
				default:
					break;
			}
		}

		for (String s : set) {
			rtn += s + ";";
		}
		if (set.size() > 0) {
			rtn = rtn.substring(0, rtn.lastIndexOf(";"));
		}
		return rtn;
	}

}
