package sageassistant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.AnalyseService;

@CrossOrigin
@RestController
public class AnalyseController {
	// private static final Logger log = LogManager.getLogger();

	@Autowired
	private AnalyseService analyseService;

	@GetMapping("/Data/AnalysisQuoteSalesCost")
	public String analyseQuoteSalesCost(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "CategoryCode", required = false, defaultValue = "") String CategoryCode,
			@RequestParam(value = "PnRoot", required = false, defaultValue = "") String PnRoot,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2999-12-30") String DateTo,
			@RequestParam(value = "Limit", required = false, defaultValue = "3") String Limit) {

		Integer limit = 3;
		try {
			limit = Integer.valueOf(Limit);
		} catch (NumberFormatException e) {
			limit = 3;
		}
		if (limit > 10) {
			limit = 10;
		}

		String categoryCode = CategoryCode;

		if (Site.equals("ALL")) {
			return analyseService.analyseQuoteSalesCostAll(categoryCode, PnRoot,DateFrom, DateTo, limit).toString();
		} else {
			return analyseService.analyseQuoteSalesCost(Site, categoryCode, PnRoot, DateFrom, DateTo, limit).toString();
		}
	}
	
	@GetMapping("/Data/AnalysisQuoteSalesCostByTarget")
	public String analyseQuoteSalesCostByTarget(
			@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
			@RequestParam(value = "PnRoot", required = false, defaultValue = "") String PnRoot,
			@RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
			@RequestParam(value = "DateTo", required = false, defaultValue = "2999-12-30") String DateTo,
			@RequestParam(value = "Limit", required = false, defaultValue = "3") String Limit,
			@RequestParam(value = "Target", required = true) String Target) {

		Integer limit = 3;
		try {
			limit = Integer.valueOf(Limit);
		} catch (NumberFormatException e) {
			limit = 3;
		}
		if (limit > 10) {
			limit = 10;
		}
		String jsonTxt = analyseService.analyseQuoteSalesCostByTarget(Site, PnRoot, DateFrom, DateTo, limit, Target);
		JSONObject json = JSON.parseObject(jsonTxt, JSONObject.class);
		return json.getString(Target);
	}
}
