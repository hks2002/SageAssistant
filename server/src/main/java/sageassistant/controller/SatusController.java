package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.StatusService;

@CrossOrigin
@RestController
public class SatusController {

	@Autowired
	private StatusService statusService;
	
	@GetMapping("/Data/TobeDelivery")
	public String getTobeDelivery(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return statusService.findTobeDeliveryBySite(site, 0).toString();
	}
	
	@GetMapping("/Data/TobeReceive")
	public String getTobeReceive(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return statusService.findTobeReceiveBySite(site, 0).toString();
	}
	
	@GetMapping("/Data/TobeDealWithOrderLine")
	public String getTobeDealWithOrderLineBySite(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return statusService.findTobeDealWithOrderLineBySite(site, 0).toString();
	}
	
	@GetMapping("/Data/TobePurchaseBom")
	public String getTobePurchaseBom(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return statusService.findTobePurchaseBomBySite(site, 0).toString();
	}

}
