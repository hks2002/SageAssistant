/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-31 16:29:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:46:48                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/CustomerController.java                   *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CustomerController {

    @Autowired
    private CustomerService CustomerService;

    @GetMapping("/Data/CustomerHelper")
    public String getCustomerName(
        @RequestParam(value = "CustomerName", required = false, defaultValue = "%%") String CustomerCodeOrName,
        @RequestParam(value = "Count", required = false, defaultValue = "50") Integer count
    ) {
        return CustomerService.getCustomerByCodeOrName(CustomerCodeOrName, count).toString();
    }

    @GetMapping("/Data/CustomerDetails")
    public String getCustomerDetails(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode
    ) {
        return CustomerService.getCustomerDetails(CustomerCode).toString();
    }

    @GetMapping("/Data/CustomerTotalAmount")
    public String getCustomerTotalAmount(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return CustomerService.getCustomerTotalAmount(CustomerCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/CustomerTotalProjectQty")
    public String getCustomerTotalProjectQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return CustomerService.getCustomerTotalProjectQty(CustomerCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/CustomerTotalItemQty")
    public String getCustomerTotalItemQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return CustomerService.getCustomerTotalItemQty(CustomerCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/CustomerTotalQty")
    public String getCustomerTotalQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return CustomerService.getCustomerTotalQty(CustomerCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/CustomerTotalProductQty")
    public String getCustomerTotalProductQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return CustomerService.getCustomerTotalProductQty(CustomerCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/CustomerOpenAmount")
    public String getCustomerOpenAmount(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode
    ) {
        return CustomerService.getCustomerOpenAmount(CustomerCode).toString();
    }

    @GetMapping("/Data/CustomerOpenProjectQty")
    public String getCustomerOpenProjectQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode
    ) {
        return CustomerService.getCustomerOpenProjectQty(CustomerCode).toString();
    }

    @GetMapping("/Data/CustomerOpenItemQty")
    public String getCustomerOpenItemQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode
    ) {
        return CustomerService.getCustomerOpenItemQty(CustomerCode).toString();
    }

    @GetMapping("/Data/CustomerOpenQty")
    public String getCustomerOpenQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode
    ) {
        return CustomerService.getCustomerOpenQty(CustomerCode).toString();
    }

    @GetMapping("/Data/CustomerOpenProductQty")
    public String getCustomerOpenProductQty(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode
    ) {
        return CustomerService.getCustomerOpenProductQty(CustomerCode).toString();
    }

    @GetMapping("/Data/CustomerDeliveryHistory")
    public String getCustomerDeliveryHistory(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return CustomerService.getCustomerDeliveryHistory(CustomerCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/CustomerDelayHistory")
    public String getCustomerDelayHistory(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode,
        @RequestParam(value = "DateFrom", required = false, defaultValue = "2000-01-01") String DateFrom,
        @RequestParam(value = "DateTo", required = false, defaultValue = "1999-12-31") String DateTo
    ) {
        return CustomerService.getCustomerDelayHistory(CustomerCode, DateFrom, DateTo).toString();
    }

    @GetMapping("/Data/CustomerOpenItems")
    public String getCustomerOpenItems(
        @RequestParam(value = "CustomerCode", required = false, defaultValue = "NULL") String CustomerCode
    ) {
        return CustomerService.getCustomerOpenItems(CustomerCode).toString();
    }
}
