/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 20:39:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:15                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/StockController.java                      *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/Data/CheckPN")
    public String checkPN(@RequestParam(value = "PN", required = false, defaultValue = "--") String PN) {
        return stockService.checkPN(PN);
    }

    @GetMapping("/Data/StockOptionPN")
    public String getStockOptionPN(@RequestParam(value = "PN", required = false, defaultValue = "--") String PN) {
        return stockService.getStockOptionPN(PN);
    }

    @GetMapping("/Data/StockQty")
    public String getStockQty(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "PN", required = false, defaultValue = "--") String PN
    ) {
        return stockService.getStockQty(Site, PN).toString();
    }

    @GetMapping("/Data/StockSummary")
    public String getStockSummary(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return stockService.getStockSummary(Site).toString();
    }

    @GetMapping("/Data/StockHistory")
    public String getStockHistory(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "PnOrName", required = false, defaultValue = "%%") String PnOrName,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return stockService.getStockHistory(Site, PnOrName, DateFrom, DateTo).toString();
    }
}
