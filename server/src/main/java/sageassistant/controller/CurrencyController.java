package sageassistant.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.CurrencyService;

@CrossOrigin
@RestController
public class CurrencyController {
	@GetMapping("/Data/CurrencyRate")
	public String getCurrencyRate(@RequestParam(value = "Date", required = true) String Date,
			@RequestParam(value = "Sour", required = true) String Sour,
			@RequestParam(value = "Dest", required = false, defaultValue = "USD") String Dest) {
		return CurrencyService.getCurrencyRate(Sour+Dest+Date);
	}
	
	@GetMapping("/Data/CurrencyRateBatch")
	public String getCurrencyRateBatch(@RequestParam(value = "query", required = true) String query) {
		return CurrencyService.getCurrencyRateBatch(query);
	}
}
