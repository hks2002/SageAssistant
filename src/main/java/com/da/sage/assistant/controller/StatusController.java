/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 20:13:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:46:31                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/StatusController.java                     *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.StatusService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class StatusController {

    //private static final Logger log = LogManager.getLogger();

    @Autowired
    private StatusService statusService;

    @GetMapping("/Data/TobeDelivery")
    public String getTobeDelivery(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return statusService.findTobeDeliveryBySite(Site).toString();
    }

    @GetMapping("/Data/TobeReceive")
    public String getTobeReceive(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return statusService.findTobeReceiveBySite(Site).toString();
    }

    @GetMapping("/Data/TobeDealWithOrderLine")
    public String getTobeDealWithOrderLineBySite(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site
    ) {
        return statusService.findTobeDealWithOrderLineBySite(Site).toString();
    }

    @GetMapping("/Data/TobePurchaseBom")
    public String getTobePurchaseBom(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site
    ) {
        return statusService.findTobePurchaseBomBySite(Site).toString();
    }

    @GetMapping("/Data/TobeClosedWO")
    public String getTobeClosedWO(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return statusService.findTobeClosedWOBySite(Site).toString();
    }

    @GetMapping("/Data/TobeTrackingSalesOrderLineCnt")
    public String getTobeTracking(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "OrderType", required = false, defaultValue = "") String OrderType,
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
        @RequestParam(value = "VendorCode", required = false, defaultValue = "") String VendorCode
    ) {
        return String.valueOf(
            statusService.findTobeTrackingSalesOrderLineCnt(Site, OrderType, CustomerCode, VendorCode)
        );
    }

    @GetMapping("/Data/TobeTrackingSalesOrderLine")
    public String getTobeTrackingSalesOrderLine(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "OrderType", required = false, defaultValue = "") String OrderType,
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
        @RequestParam(value = "VendorCode", required = false, defaultValue = "") String VendorCode,
        @RequestParam(value = "OrderBy", required = false, defaultValue = "DaysLeft") String OrderBy,
        @RequestParam(value = "Descending", required = false, defaultValue = "ASC") String Descending,
        @RequestParam(value = "Offset", required = false, defaultValue = "0") Integer Offset,
        @RequestParam(value = "Limit", required = false, defaultValue = "50") Integer Limit
    ) {
        return statusService
            .findTobeTrackingSalesOrderLine(
                Site,
                OrderType,
                CustomerCode,
                VendorCode,
                OrderBy,
                Descending,
                Offset,
                Limit
            )
            .toString();
    }

    @GetMapping("/Data/TobeTrackingBOMLine")
    public String getTobeTrackingBOMLine(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "OrderType", required = false, defaultValue = "") String OrderType,
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
        @RequestParam(value = "VendorCode", required = false, defaultValue = "") String VendorCode,
        @RequestParam(value = "OrderBy", required = false, defaultValue = "DaysLeft") String OrderBy,
        @RequestParam(value = "Descending", required = false, defaultValue = "ASC") String Descending,
        @RequestParam(value = "Offset", required = false, defaultValue = "0") Integer Offset,
        @RequestParam(value = "Limit", required = false, defaultValue = "50") Integer Limit
    ) {
        return statusService
            .findTobeTrackingBOMLine(Site, OrderType, CustomerCode, VendorCode, OrderBy, Descending, Offset, Limit)
            .toString();
    }

    @GetMapping("/Data/TobeTrackingPurchaseOrderLine")
    public String getTobeTrackingPurchaseOrderLine(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "OrderType", required = false, defaultValue = "") String OrderType,
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
        @RequestParam(value = "VendorCode", required = false, defaultValue = "") String VendorCode,
        @RequestParam(value = "OrderBy", required = false, defaultValue = "DaysLeft") String OrderBy,
        @RequestParam(value = "Descending", required = false, defaultValue = "ASC") String Descending,
        @RequestParam(value = "Offset", required = false, defaultValue = "0") Integer Offset,
        @RequestParam(value = "Limit", required = false, defaultValue = "50") Integer Limit
    ) {
        return statusService
            .findTobeTrackingPurchaseOrderLine(
                Site,
                OrderType,
                CustomerCode,
                VendorCode,
                OrderBy,
                Descending,
                Offset,
                Limit
            )
            .toString();
    }

    @GetMapping("/Data/TobeTrackingReceiptLine")
    public String getTobeTrackingReceiptLine(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "OrderType", required = false, defaultValue = "") String OrderType,
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
        @RequestParam(value = "VendorCode", required = false, defaultValue = "") String VendorCode,
        @RequestParam(value = "OrderBy", required = false, defaultValue = "DaysLeft") String OrderBy,
        @RequestParam(value = "Descending", required = false, defaultValue = "ASC") String Descending,
        @RequestParam(value = "Offset", required = false, defaultValue = "0") Integer Offset,
        @RequestParam(value = "Limit", required = false, defaultValue = "50") Integer Limit
    ) {
        return statusService
            .findTobeTrackingReceiptLine(Site, OrderType, CustomerCode, VendorCode, OrderBy, Descending, Offset, Limit)
            .toString();
    }

    @GetMapping("/Data/TobeTrackingQALine")
    public String getTobeTrackingQALine(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site,
        @RequestParam(value = "OrderType", required = false, defaultValue = "") String OrderType,
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "") String CustomerCode,
        @RequestParam(value = "VendorCode", required = false, defaultValue = "") String VendorCode,
        @RequestParam(value = "OrderBy", required = false, defaultValue = "DaysLeft") String OrderBy,
        @RequestParam(value = "Descending", required = false, defaultValue = "ASC") String Descending,
        @RequestParam(value = "Offset", required = false, defaultValue = "0") Integer Offset,
        @RequestParam(value = "Limit", required = false, defaultValue = "50") Integer Limit
    ) {
        return statusService
            .findTobeTrackingQALine(Site, OrderType, CustomerCode, VendorCode, OrderBy, Descending, Offset, Limit)
            .toString();
    }
}
