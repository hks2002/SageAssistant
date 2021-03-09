package sageassistant.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import sageassistant.dao.AttachmentMapper;
import sageassistant.model.Attachment;
import sageassistant.utils.Utils;

@Service
public class AttachmentService {
	private static final Logger log = LoggerFactory.getLogger(AttachmentService.class);
	
	@Autowired
	private AttachmentMapper attachmentMapper;

	@Value("${attachment.path.linux}")
	private String attachmentPathLinux;

	@Value("${attachment.path.windows}")
	private String attachmentPathWindows;
	
	public String handleFileUpload(String pn, String cat, final MultipartFile uploadfile, HttpServletResponse response) {

		if (!Utils.isServerAtZhuhai()) {
			response.setStatus(403);
			return "Server doesn't support this action!";
		}
		
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

			return "Upload file with success!";
		} catch (IOException e) {
			log.error("[Upload] " + e.toString());
			response.setStatus(500);
			return "Upload file failed!";
		}

	}
	
	public String handleFileDelete(String file,HttpServletResponse response) {
		if (!Utils.isServerAtZhuhai()) {
			response.setStatus(403);
			return "Server doesn't support this action!";
		}
		
		String attachmentPath = Utils.isWin() ? attachmentPathWindows : attachmentPathLinux;
		Path path = Paths.get(attachmentPath + file.replaceFirst("^(/File)", ""));

		try {
			Files.delete(path);
			log.info("[Delete] " + path);
			return "Delete file with success!";
		} catch (IOException e) {
			log.error("[Delete] " + e.toString());
			response.setStatus(500);
			return "Delete file failed!";
		}
	}
	
	public String getAttachmentPath(String pn) {
		if (pn.equals("NULL")) {
			return "[]";
		}

		if (!Utils.isServerAtZhuhai()) {
			List<Attachment> listAttachment=attachmentMapper.getAttachment(pn);
			for (Attachment o : listAttachment) {
				
				// make the url with server path
				String pathStd = o.getPath();
				pathStd=pathStd.replace("\\", "/");
				
				Path path = Paths.get(pathStd, "");
				o.setFile(path.getFileName().toString());
				
				// stand path is /File/docs_sagex3/*
				if (pathStd.startsWith("%")) {
					if (pathStd.toLowerCase().contains("docs_sagex3")) {
						pathStd="/File/" + pathStd.substring(1);
					} else {
						pathStd="/File/docs_sagex3/" + pathStd.substring(1);
					}
				} else if (pathStd.startsWith("//")) {
					if (pathStd.toLowerCase().startsWith("//srvdata01/docs_sagex3")) {
						pathStd= "/File/" + pathStd.substring(12);
					} else if (pathStd.toLowerCase().startsWith("//192.168.10.47/docs_sagex3")) {
						pathStd= "/File/" + pathStd.substring(16);
					}
					
				} else {
					pathStd="/File/docs_sagex3/";
				}
				
				o.setPath(pathStd);
			}
			return listAttachment.toString();
			
		} else { // Zhuhai
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
