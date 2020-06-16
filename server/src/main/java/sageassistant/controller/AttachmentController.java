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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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

		// change / \ * ? to -
		String shortPn = Utils.makeShortPn(pn.replaceAll("(\\\\|\\*|\\/|\\?)", "-"));
		// Remove version
		String shortPnRoot = shortPn.replaceAll("_\\S+$", "");

		Path path = null;

		if (cat.equals("Manual")) {
			path = Paths.get(attachmentPath + "/Manual/" + shortPnRoot + "/" + uploadfile.getOriginalFilename());
		} else if (cat.equals("Drawing")) {
			path = Paths.get(attachmentPath + "/Drawing/" + shortPn + "/" + uploadfile.getOriginalFilename());
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

	/*
	 * @param pn could be Pn or PnRoot, PnRoot without version
	 */
	@GetMapping("/Data/AttachmentPath")
	public String getAttachmentPath(@RequestParam(value = "Pn", required = false, defaultValue = "NULL") String pn) {
		if (pn.equals("NULL")) {
			return "[]";
		}

		// change / \ * ? to -
		String pnShort = pn.replaceAll("(\\\\|\\*|\\/|\\?)", "-");
		pnShort = Utils.makeShortPn(pnShort);


		//Manual's folder without version end with '_', if pn with version, ManualsShort is empty, only return Drawing.
		//And if Pn without version and Pn==PnRoot, both return Manual and Drawing
		JSONArray ManualsShort = makeJsonAarry(pn, "Manual", pnShort);
		JSONArray DrawingShort = makeJsonAarry(pn, "Drawing", pnShort);

		
		JSONArray all = new JSONArray();

		all.addAll(ManualsShort);
		all.addAll(DrawingShort);

		return all.toJSONString();
	}

	private JSONArray makeJsonAarry(String pn, String catStr, String folder) {
		JSONArray arr = new JSONArray();

		String attachmentPath = Utils.isWin() ? attachmentPathWindows : attachmentPathLinux;

		String[] files = Utils.findFiles(attachmentPath + "/" + catStr + "/" + folder);

		for (int i = 0, l = files.length; i < l; i++) {
			JSONObject obj = new JSONObject();
			obj.put("PN", pn);
			obj.put("Cat", catStr);
			obj.put("Path", "/File/" + catStr + "/" + folder + "/" + files[i]);
			obj.put("File", files[i]);
			obj.put("DocType", Utils.getFileExt(files[i]));

			arr.add(obj);
		}
		return arr;
	}
}
