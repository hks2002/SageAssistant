package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.CurrencyService;

@CrossOrigin
@RestController
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@GetMapping("/Data/CurrencyRate")
	public String getCurrencyRate(String Sour, String Dest, String Date) {
		return currencyService.getCurrencyRate(Sour + Dest + Date);
	}

	@GetMapping("/Data/CurrencyRateBatch")
	public String getCurrencyRateBatch(@RequestParam(value = "query", required = true) String query) {
		return currencyService.getCurrencyRateBatch(query);
	}
}
