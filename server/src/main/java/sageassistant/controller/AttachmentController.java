package sageassistant.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class AttachmentController {
	private static final Logger log = LoggerFactory.getLogger(AttachmentController.class);

	@Value("${attachment.path.linux}")
	private String attachmentPathLinux;

	@Value("${attachment.path.windows}")
	private String attachmentPathWindows;

	@PostMapping("/Data/FileUpload")
	public String handleFileUpload(@RequestParam(value = "Pn", required = true) String pn,
			@RequestParam(value = "Cat", required = true) String cat,
			@RequestParam("file") final MultipartFile uploadfile, HttpServletResponse response) {

		String attachmentPath = Utils.isWin() ? attachmentPathWindows : attachmentPathLinux;
		String shortPn = Utils.makeShortPn(pn);
				
		Path path = null;

		if (cat.equals("4")) {
			path = Paths
					.get(attachmentPath + "/" + shortPn + "/Design/" + uploadfile.getOriginalFilename());
		} else if (cat.equals("5")) {
			path = Paths
					.get(attachmentPath + "/" + shortPn + "/Drawing/" + uploadfile.getOriginalFilename());
		} else if (cat.equals("6")) {
			path = Paths.get(attachmentPath + "/" + shortPn + "/TestingCertificate/"
					+ uploadfile.getOriginalFilename());
		} else if (cat.equals("8")) {
			path = Paths
					.get(attachmentPath + "/" + shortPn + "/Manual/" + uploadfile.getOriginalFilename());
		} else if (cat.equals("9")) {
			path = Paths.get(
					attachmentPath + "/" + shortPn + "/DataSheet/" + uploadfile.getOriginalFilename());
		} else if (cat.equals("12")) {
			path = Paths.get(
					attachmentPath + "/" + shortPn+ "/QulitySheet/" + uploadfile.getOriginalFilename());
		} else {
			log.error("[Upload] [" + pn + "]" + path);
		}

		byte[] bytes;
		try {
			log.info("[Upload] [" + shortPn + "] " + path);
			bytes = uploadfile.getBytes();
			Files.write(path, bytes);
			
			return "{\"result\":\"OK\"}";
		} catch (IOException e) {
			log.error("[Upload] " + e.toString());
			response.setStatus(500);
			return "{\"result\":\"Not OK\"}";
		}

	}

	@GetMapping("/Data/FileDelete")
	public String handleFileDelete(@RequestParam(value = "Path", required = true) String file,
			HttpServletResponse response) {

		String attachmentPath = Utils.isWin() ? attachmentPathWindows : attachmentPathLinux;
		Path path = Paths.get(attachmentPath + file.replaceFirst("^(/File)", ""));

		try {
			Files.delete(path);
			log.info("[Delete] " + path);
			return "{\"result\":\"OK\"}";
		} catch (IOException e) {
			log.error("[Delete] " + e.toString());
			response.setStatus(500);
			return "{\"result\":\"Not OK\"}";
		}
	}

}
