/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 20:19:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:46:57                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/CurrencyController.java                   *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.CurrencyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CurrencyController {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/Data/CurrencyRate")
    public String getCurrencyRate(
        @RequestParam(value = "Sour", required = true) String Sour,
        @RequestParam(value = "Dest", required = true) String Dest,
        @RequestParam(value = "Date", required = true) String Date
    ) {
        log.debug(Sour + Dest + Date);
        return currencyService.getFromSage(Sour + Dest + Date);
    }

    @GetMapping("/Data/CurrencyRateBatch")
    public String getCurrencyRateBatch(@RequestParam(value = "Query", required = true) String Query) {
        log.debug(Query);
        return currencyService.getCurrencyRateBatch(Query);
    }
}
