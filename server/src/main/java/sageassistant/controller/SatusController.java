package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.StatusService;
import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class SatusController {

	@Autowired
	private StatusService statusService;
	
	@GetMapping("/Data/TobeDelivery")
	public String getTobeDelivery(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return Utils.listToString(statusService.findTobeDeliveryBySite(site, 0));
	}
	
	@GetMapping("/Data/TobeReceive")
	public String getTobeReceive(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return Utils.listToString(statusService.findTobeReceiveBySite(site, 0));
	}
	
	@GetMapping("/Data/TobeDealWithOrderLine")
	public String getTobeDealWithOrderLineBySite(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return Utils.listToString(statusService.findTobeDealWithOrderLineBySite(site, 0));
	}
	
	@GetMapping("/Data/TobePurchaseBom")
	public String getTobePurchaseBom(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return Utils.listToString(statusService.findTobePurchaseBomBySite(site, 0));
	}
	
	@GetMapping("/Data/TobeClosedWO")
	public String getTobeClosedWO(@RequestParam(value = "site", required = false, defaultValue = "ZHU") String site) {
		return Utils.listToString(statusService.findTobeClosedWOBySite(site, 0));
	}

}
