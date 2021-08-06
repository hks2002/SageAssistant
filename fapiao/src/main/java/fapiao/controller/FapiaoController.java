package fapiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fapiao.service.FapiaoService;

@CrossOrigin
@RestController
public class FapiaoController {
	@Autowired
	FapiaoService fapiaoService;

	@GetMapping("/Header")
	public String getHeader(@RequestParam(value = "Lbdm", required = false, defaultValue = "NULL") String Lbdm,
			@RequestParam(value = "Fphm", required = false, defaultValue = "NULL") String Fphm) {
		if (Lbdm.equals("NULL")) {
			return fapiaoService.getHeader(Fphm).toString();
		} else {
			return fapiaoService.getHeader(Lbdm, Fphm).toString();
		}
	}

	@GetMapping("/Body")
	public String getBody(@RequestParam(value = "Lbdm", required = false, defaultValue = "NULL") String Lbdm,
			@RequestParam(value = "Fphm", required = false, defaultValue = "NULL") String Fphm) {
		if (Lbdm.equals("NULL")) {
			return fapiaoService.getBody(Fphm).toString();
		} else {
			return fapiaoService.getBody(Lbdm, Fphm).toString();
		}
	}

	@GetMapping("/Lbdm")
	public String getLbdm(@RequestParam(value = "Lbdm", required = false, defaultValue = "NULL") String Lbdm) {
		return fapiaoService.getLbdm(Lbdm).toString();
	}

	@GetMapping("/Fphm")
	public String getFphm(@RequestParam(value = "Lbdm", required = false, defaultValue = "NULL") String Lbdm,
			@RequestParam(value = "Fphm", required = false, defaultValue = "NULL") String Fphm) {
		if (Lbdm.equals("NULL")) {
			return fapiaoService.getFphm(Fphm).toString();
		} else {
			return fapiaoService.getFphm(Lbdm, Fphm).toString();
		}
	}
}
