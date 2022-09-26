/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 20:19:45
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:45:49
 * @FilePath       : \server\src\main\java\sageassistant\controller\CurrencyController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.CurrencyService;

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
