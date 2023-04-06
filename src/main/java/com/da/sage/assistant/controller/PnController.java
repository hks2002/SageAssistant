/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 19:06:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:30                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/PnController.java                         *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.alibaba.fastjson.JSONArray;
import com.da.sage.assistant.service.PnService;
import com.da.sage.assistant.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class PnController {

    @Autowired
    private PnService pnService;

    @GetMapping("/Data/PNHelper")
    public String getPNs(
        @RequestParam(value = "PN", required = false, defaultValue = "%%") String pnOrPnRoot,
        @RequestParam(value = "Count", required = false, defaultValue = "200") Integer Count
    ) {
        return JSONArray.toJSONString(pnService.findPnByStartWith(pnOrPnRoot, Count));
    }

    @GetMapping("/Data/MakeShortPn")
    public String makeShortPn(@RequestParam(value = "Pn", required = true) String PN) {
        return Utils.makeShortPn(PN);
    }

    @GetMapping("/Data/PNsInFamily")
    public String getPNsInFamily(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return pnService.findAllPnByPnRoot(PnRoot).toString();
    }

    @GetMapping("/Data/SalesHistory")
    public String getSalesHistory(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return pnService.findSalesHistoryByPnRoot(PnRoot).toString();
    }

    @GetMapping("/Data/QuoteHistory")
    public String getQuoteHistory(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return pnService.findQuoteHistoryByPnRoot(PnRoot).toString();
    }

    @GetMapping("/Data/CostHistory")
    public String getCostHistory(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return pnService.findCostHistoryByPnRoot(PnRoot).toString();
    }

    @GetMapping("/Data/DeliveryDuration")
    public String getDeliveryDuration(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return pnService.findDeliveryDurationByPnRoot(PnRoot).toString();
    }

    @GetMapping("/Data/InventoryStock")
    public String getInventoryStock(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return pnService.findStockInfoByPnRoot(PnRoot).toString();
    }
}
