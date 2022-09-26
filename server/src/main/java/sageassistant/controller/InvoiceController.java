/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 21:57:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-25 12:39:19
 * @FilePath       : \server\src\main\java\sageassistant\controller\InvoiceController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.InvoiceService;

@CrossOrigin
@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/Data/InvoiceNO")
    public String getInvoiceNO(
        @RequestParam(value = "InvoiceNO", required = false, defaultValue = "NULL") String InvoiceNO,
        @RequestParam(value = "Count", required = false, defaultValue = "10") Integer Count
    ) {
        return invoiceService.findInvoiceNOByInvoiceNO(InvoiceNO, Count);
    }

    @GetMapping("/Data/InvoiceHeaderByInvoiceNO")
    public String getInvoiceHeader(
        @RequestParam(value = "InvoiceNO", required = false, defaultValue = "NULL") String InvoiceNO
    ) {
        return invoiceService.findInvoiceHeaderByInvoiceNO(InvoiceNO).toString();
    }

    @GetMapping("/Data/InvoiceBodyByInvoiceNO")
    public String getInvoiceBody(
        @RequestParam(value = "InvoiceNO", required = false, defaultValue = "NULL") String InvoiceNO
    ) {
        return invoiceService.findInvoiceBodyByInvoiceNO(InvoiceNO).toString();
    }

    @GetMapping("/Data/InvoiceHeaderByFaPiao")
    public String getInvoiceHeaderByFaPiao(
        @RequestParam(value = "FaPiao", required = false, defaultValue = "NULL") String FaPiao
    ) {
        return invoiceService.findInvoiceHeaderByFaPiao(FaPiao).toString();
    }

    @GetMapping("/Data/InvoiceBodyByFaPiao")
    public String getInvoiceBodyByFaPiao(
        @RequestParam(value = "FaPiao", required = false, defaultValue = "NULL") String FaPiao
    ) {
        return invoiceService.findInvoiceBodyByFaPiao(FaPiao).toString();
    }
}
