/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 19:06:42
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:46:15
 * @FilePath       : \server\src\main\java\sageassistant\controller\PnController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.PnService;
import sageassistant.utils.Utils;

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
        return Utils.listToString(pnService.findPnByStartWith(pnOrPnRoot, Count));
    }

    @GetMapping("/Data/MakeShortPn")
    public String makeShortPn(@RequestParam(value = "Pn", required = true) String PN) {
        return Utils.makeShortPn(PN);
    }

    @GetMapping("/Data/PNsInFamily")
    public String getPNsInFamily(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return Utils.listToString(pnService.findAllPnByPnRoot(PnRoot));
    }

    @GetMapping("/Data/SalesHistory")
    public String getSalesHistory(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return Utils.listToString(pnService.findSalesHistoryByPnRoot(PnRoot));
    }

    @GetMapping("/Data/QuoteHistory")
    public String getQuoteHistory(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return Utils.listToString(pnService.findQuoteHistoryByPnRoot(PnRoot));
    }

    @GetMapping("/Data/CostHistory")
    public String getCostHistory(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return Utils.listToString(pnService.findCostHistoryByPnRoot(PnRoot));
    }

    @GetMapping("/Data/DeliveryDuration")
    public String getDeliveryDuration(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return Utils.listToString(pnService.findDeliveryDurationByPnRoot(PnRoot));
    }

    @GetMapping("/Data/InventoryStock")
    public String getInventoryStock(
        @RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String PnRoot
    ) {
        return Utils.listToString(pnService.findStockInfoByPnRoot(PnRoot));
    }
}
