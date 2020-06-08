package sageassistant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.ReportService;

@CrossOrigin
@RestController
public class ReportController {
	
	@Autowired
	ReportService reportService;

	@GetMapping("/Report/*/*")
	public void handReportRequest(HttpServletRequest request, HttpServletResponse response) {
			reportService.handRequest(request, response);
	}
}
