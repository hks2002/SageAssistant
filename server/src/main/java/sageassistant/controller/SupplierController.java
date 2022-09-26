/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 21:35:51
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:46:46
 * @FilePath       : \server\src\main\java\sageassistant\controller\SupplierController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.SupplierService;
import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class SupplierController {

    @Autowired
    private SupplierService SupplierService;

    @GetMapping("/Data/SupplierHelper")
    public String getSupplierName(
        @RequestParam(value = "SupplierName", required = false, defaultValue = "%%") String SupplierCodeOrName,
        @RequestParam(value = "Count", required = false, defaultValue = "50") Integer count
    ) {
        return Utils.listToString(SupplierService.getSupplierByCodeOrName(SupplierCodeOrName, count));
    }

    @GetMapping("/Data/SupplierDetails")
    public String getSupplierDetails(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return SupplierService.getSupplierDetails(SupplierCode).toString();
    }

    @GetMapping("/Data/SupplierTotalAmount")
    public String getSupplierTotalAmount(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return Utils.listToString(SupplierService.getSupplierTotalAmount(SupplierCode, DateFrom, DateTo));
    }

    @GetMapping("/Data/SupplierTotalProjectQty")
    public String getSupplierTotalProjectQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return Utils.listToString(SupplierService.getSupplierTotalProjectQty(SupplierCode, DateFrom, DateTo));
    }

    @GetMapping("/Data/SupplierTotalItemQty")
    public String getSupplierTotalItemQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return Utils.listToString(SupplierService.getSupplierTotalItemQty(SupplierCode, DateFrom, DateTo));
    }

    @GetMapping("/Data/SupplierTotalQty")
    public String getSupplierTotalQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return SupplierService.getSupplierTotalQty(SupplierCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/SupplierTotalProductQty")
    public String getSupplierTotalProductQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return SupplierService.getSupplierTotalProductQty(SupplierCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/SupplierOpenAmount")
    public String getSupplierOpenAmount(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return Utils.listToString(SupplierService.getSupplierOpenAmount(SupplierCode));
    }

    @GetMapping("/Data/SupplierOpenProjectQty")
    public String getSupplierOpenProjectQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return Utils.listToString(SupplierService.getSupplierOpenProjectQty(SupplierCode));
    }

    @GetMapping("/Data/SupplierOpenItemQty")
    public String getSupplierOpenItemQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return Utils.listToString(SupplierService.getSupplierOpenItemQty(SupplierCode));
    }

    @GetMapping("/Data/SupplierOpenQty")
    public String getSupplierOpenQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return Utils.listToString(SupplierService.getSupplierOpenQty(SupplierCode));
    }

    @GetMapping("/Data/SupplierOpenProductQty")
    public String getSupplierOpenProductQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return Utils.listToString(SupplierService.getSupplierOpenProductQty(SupplierCode));
    }

    @GetMapping("/Data/SupplierDeliveryHistory")
    public String getSupplierDeliveryHistory(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return Utils.listToString(SupplierService.getSupplierDeliveryHistory(SupplierCode, DateFrom, DateTo));
    }

    @GetMapping("/Data/SupplierDelayHistory")
    public String getSupplierDelayHistory(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return Utils.listToString(SupplierService.getSupplierDelayHistory(SupplierCode, DateFrom, DateTo));
    }

    @GetMapping("/Data/SupplierOpenItems")
    public String getSupplierOpenItems(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return Utils.listToString(SupplierService.getSupplierOpenItems(SupplierCode));
    }

    @GetMapping("/Data/PurchaseDate")
    public String getPurchaseDate(
        @RequestParam(value = "PurchaseNO", required = true, defaultValue = "NULL") String PurchaseNO
    ) {
        return SupplierService.getPurchaseDate(PurchaseNO);
    }
}
