/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 21:57:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:40                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/InvoiceController.java                    *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
