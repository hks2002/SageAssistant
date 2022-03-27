package sageassistant.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sageassistant.service.AttachmentService;

@CrossOrigin
@RestController
public class AttachmentController {
	
	@Autowired
	AttachmentService attachmentService;

	@PostMapping("/Data/FileUpload")
	public String handleFileUpload(@RequestParam(value = "Pn", required = true) String pn,
			@RequestParam(value = "Cat", required = true) String cat,
			@RequestParam("file") final MultipartFile uploadfile, HttpServletResponse response) {
		return attachmentService.handleFileUpload(pn, cat, uploadfile, response);
	}

	@GetMapping("/Data/FileDelete")
	public String handleFileDelete(@RequestParam(value = "Path", required = true) String file,
			HttpServletResponse response) {
		return attachmentService.handleFileDelete(file, response);
	}

	/*
	 * @param pn could be Pn or PnRoot, PnRoot without version
	 */
	@GetMapping("/Data/AttachmentPath")
	public String getAttachmentPath(@RequestParam(value = "Pn", required = false, defaultValue = "NULL") String pn) {
		return attachmentService.getAttachmentPath(pn);
	}


}
