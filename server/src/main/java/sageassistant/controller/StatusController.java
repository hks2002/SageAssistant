/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 20:13:40
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:46:35
 * @FilePath       : \server\src\main\java\sageassistant\controller\StatusController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.StatusService;
import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class StatusController {

    // private static final Logger log = LogManager.getLogger();

    @Autowired
    private StatusService statusService;

    @GetMapping("/Data/TobeDelivery")
    public String getTobeDelivery(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return Utils.listToString(statusService.findTobeDeliveryBySite(Site, 0));
    }

    @GetMapping("/Data/TobeReceive")
    public String getTobeReceive(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return Utils.listToString(statusService.findTobeReceiveBySite(Site, 0));
    }

    @GetMapping("/Data/TobeDealWithOrderLine")
    public String getTobeDealWithOrderLineBySite(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site
    ) {
        return Utils.listToString(statusService.findTobeDealWithOrderLineBySite(Site, 0));
    }

    @GetMapping("/Data/TobePurchaseBom")
    public String getTobePurchaseBom(
        @RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site
    ) {
        return Utils.listToString(statusService.findTobePurchaseBomBySite(Site, 0));
    }

    @GetMapping("/Data/TobeClosedWO")
    public String getTobeClosedWO(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return Utils.listToString(statusService.findTobeClosedWOBySite(Site, 0));
    }

    @GetMapping("/Data/TobeTracking")
    public String getTobeTracking(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
        return Utils.listToString(statusService.findTobeTracking(Site));
    }
}
