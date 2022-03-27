package sageassistant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sageassistant.service.HttpService;

@RestController
public class HttpController {

	@Autowired
	HttpService httpService;

	@RequestMapping("/Proxy/**/*")
	public void doRequest(HttpServletRequest request, HttpServletResponse response) {
		httpService.doRequest(request, response);
	}

}
