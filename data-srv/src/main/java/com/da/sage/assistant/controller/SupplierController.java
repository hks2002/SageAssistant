/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 21:35:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:20                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/SupplierController.java                   *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return SupplierService.getSupplierByCodeOrName(SupplierCodeOrName, count).toString();
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
        return SupplierService.getSupplierTotalAmount(SupplierCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/SupplierTotalProjectQty")
    public String getSupplierTotalProjectQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return SupplierService.getSupplierTotalProjectQty(SupplierCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/SupplierTotalItemQty")
    public String getSupplierTotalItemQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return SupplierService.getSupplierTotalItemQty(SupplierCode, DateFrom, DateTo).toString();
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
        return SupplierService.getSupplierOpenAmount(SupplierCode).toString();
    }

    @GetMapping("/Data/SupplierOpenProjectQty")
    public String getSupplierOpenProjectQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return SupplierService.getSupplierOpenProjectQty(SupplierCode).toString();
    }

    @GetMapping("/Data/SupplierOpenItemQty")
    public String getSupplierOpenItemQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return SupplierService.getSupplierOpenItemQty(SupplierCode).toString();
    }

    @GetMapping("/Data/SupplierOpenQty")
    public String getSupplierOpenQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return SupplierService.getSupplierOpenQty(SupplierCode).toString();
    }

    @GetMapping("/Data/SupplierOpenProductQty")
    public String getSupplierOpenProductQty(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return SupplierService.getSupplierOpenProductQty(SupplierCode).toString();
    }

    @GetMapping("/Data/SupplierDeliveryHistory")
    public String getSupplierDeliveryHistory(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return SupplierService.getSupplierDeliveryHistory(SupplierCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/SupplierDelayHistory")
    public String getSupplierDelayHistory(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return SupplierService.getSupplierDelayHistory(SupplierCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/SupplierOpenItems")
    public String getSupplierOpenItems(
        @RequestParam(value = "SupplierCode", required = false, defaultValue = "NULL") String SupplierCode
    ) {
        return SupplierService.getSupplierOpenItems(SupplierCode).toString();
    }

    @GetMapping("/Data/PurchaseDate")
    public String getPurchaseDate(
        @RequestParam(value = "PurchaseNO", required = true, defaultValue = "NULL") String PurchaseNO
    ) {
        return SupplierService.getPurchaseDate(PurchaseNO);
    }
}
