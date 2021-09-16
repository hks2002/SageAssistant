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
	public String getTobeDelivery(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeDeliveryBySite(Site, 0));
	}
	
	@GetMapping("/Data/TobeReceive")
	public String getTobeReceive(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeReceiveBySite(Site, 0));
	}
	
	@GetMapping("/Data/TobeDealWithOrderLine")
	public String getTobeDealWithOrderLineBySite(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeDealWithOrderLineBySite(Site, 0));
	}
	
	@GetMapping("/Data/TobePurchaseBom")
	public String getTobePurchaseBom(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobePurchaseBomBySite(Site, 0));
	}
	
	@GetMapping("/Data/TobeClosedWO")
	public String getTobeClosedWO(@RequestParam(value = "Site", required = false, defaultValue = "ZHU") String Site) {
		return Utils.listToString(statusService.findTobeClosedWOBySite(Site, 0));
	}

}
