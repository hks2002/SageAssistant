package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.CommonService;

@CrossOrigin
@RestController
public class CommonController {	 
	@Autowired
	CommonService commonService;

	@GetMapping("/Data/Sites")
	public String getAllSites() {
		return commonService.getAllSites();
	}

}
