package fapiao.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SrvInfoController {	 


	@GetMapping("/")
	public String getInfo() {
		return "恭喜，发票服务已经运行！";
	}
	
}
