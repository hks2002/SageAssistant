package sageassistant.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sageassistant.dao.AnalyseMapper;
import sageassistant.dao.CommonMapper;
import sageassistant.model.AnalyseQuoteSalesCost;

@Service
public class AnalyseService {
	// private static final Logger log = LogManager.getLogger();

	@Autowired
	private AnalyseMapper analyseMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	public List<AnalyseQuoteSalesCost> analyseQuoteSalesCost(@Param("Site") String Site,
			@Param("CategoryCode") String CategoryCode, @Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo, @Param("Limit") Integer Limit) {
		return analyseMapper.analyseQuoteSalesCost(Site, CategoryCode, DateFrom, DateTo, Limit);
	}
	

	public List<AnalyseQuoteSalesCost> analyseQuoteSalesCostAll(
			@Param("CategoryCode") String CategoryCode, @Param("DateFrom") String DateFrom,
			@Param("DateTo") String DateTo, @Param("Limit") Integer Limit) {
		
		List<AnalyseQuoteSalesCost> listAll = new ArrayList<>();
		for (String o : commonMapper.getAllSites() ) {
			listAll.addAll(analyseMapper.analyseQuoteSalesCost(o, CategoryCode, DateFrom, DateTo, Limit));
		}
		return listAll;
	}

}
