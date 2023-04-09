/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 21:46:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:50                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/AnalysesController.java                   *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.alibaba.fastjson.JSONArray;
import com.da.sage.assistant.service.AnalysesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AnalysesController {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private AnalysesService analysesService;

    @GetMapping("/Data/AnalysesQuoteSalesCost")
    public String analysesQuoteSalesCost(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "CategoryCode", required = false, defaultValue = "") String CategoryCode,
        @RequestParam(value = "PnRoot", required = false, defaultValue = "") String PnRoot,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "2999-12-30") String DateTo,
        @RequestParam(value = "Limit", required = false, defaultValue = "3") String Limit
    ) {
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
            return analysesService.analysesQuoteSalesCostAll(categoryCode, PnRoot, DateFrom, DateTo, limit).toString();
        } else {
            return analysesService
                .analysesQuoteSalesCost(Site, categoryCode, PnRoot, DateFrom, DateTo, limit)
                .toString();
        }
    }

    @GetMapping("/Data/AnalysesQuoteSalesCostByTarget")
    public String analysesQuoteSalesCostByTarget(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "PnRoot", required = false, defaultValue = "") String PnRoot,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "2999-12-30") String DateTo,
        @RequestParam(value = "Limit", required = false, defaultValue = "3") String Limit,
        @RequestParam(value = "Target", required = true) String Target
    ) {
        Integer limit = 3;
        try {
            limit = Integer.valueOf(Limit);
        } catch (NumberFormatException e) {
            limit = 3;
        }
        if (limit > 10) {
            limit = 10;
        }
        return analysesService.analysesQuoteSalesCostByTarget(Site, PnRoot, DateFrom, DateTo, limit, Target).toString();
    }

    @GetMapping("/Data/AnalysesPurchase")
    public String analysesPurchase(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "PN", required = false, defaultValue = "") String PnRoot,
        @RequestParam(value = "Currency", required = false, defaultValue = "USD") String Currency,
        @RequestParam(value = "Target", required = false, defaultValue = "NetPrice") String Target,
        @RequestParam(value = "LastN", required = false, defaultValue = "1") String LastN
    ) {
        if (PnRoot.isEmpty()) {
            log.info("request url error");
            return "https://sageassistant/Data/AnalysesPurchase?Site=SITE&PN=PN&Currency=USD&Target=NetPrice&LastN=1\nAvailable Target:NetPrice,ProjectNO,PurchaseDate,PurchaseNO,PurchaseDate";
        }
        return JSONArray.toJSONString(analysesService.analysesPurchase(Site, PnRoot, Currency, Target, LastN));
    }

    @GetMapping("/Data/AnalysesQuote")
    public String analysesQuote(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "PN", required = false, defaultValue = "") String PnRoot,
        @RequestParam(value = "Currency", required = false, defaultValue = "USD") String Currency,
        @RequestParam(value = "Target", required = false, defaultValue = "NetPrice") String Target,
        @RequestParam(value = "LastN", required = false, defaultValue = "1") String LastN
    ) {
        if (PnRoot.isEmpty()) {
            log.info("request url error");
            return "https://sageassistant/Data/AnalysesQuote?Site=SITE&PN=PN&Currency=USD&Target=NetPrice&LastN=1\nAvailable Target:NetPrice,QuoteNO,QuoteDate,CustomerCode,CustomerName,OrderNO,OrderFlag,QTY";
        }
        return JSONArray.toJSONString(analysesService.analysesQuote(Site, PnRoot, Currency, Target, LastN));
    }

    @GetMapping("/Data/AnalysesSales")
    public String analysesSales(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "PN", required = false, defaultValue = "") String PnRoot,
        @RequestParam(value = "Currency", required = false, defaultValue = "USD") String Currency,
        @RequestParam(value = "Target", required = false, defaultValue = "NetPrice") String Target,
        @RequestParam(value = "LastN", required = false, defaultValue = "1") String LastN
    ) {
        if (PnRoot.isEmpty()) {
            log.info("request url error");
            return "https://sageassistant/Data/AnalysesSales?Site=SITE&PN=PN&Currency=USD&Target=NetPrice&LastN=1\nAvailable Target:NetPrice,OrderNO,OrderDate,CustomerCode,CustomerName,QTY";
        }
        return JSONArray.toJSONString(analysesService.analysesSales(Site, PnRoot, Currency, Target, LastN));
    }
}
