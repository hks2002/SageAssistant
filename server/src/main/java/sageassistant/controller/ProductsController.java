package sageassistant.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;

import sageassistant.service.PnService;
import sageassistant.utils.Utils;

@CrossOrigin
@RestController
public class ProductsController {
	//private static final Logger log = LoggerFactory.getLogger(ProductsController.class);
	
	@Value("${attachment.path.linux}")
	private String attachmentPathLinux;

	@Value("${attachment.path.windows}")
	private String attachmentPathWindows;

	@Autowired
	private PnService pnService;

	static ReportClientDocument reportClientDocument = new ReportClientDocument();
	
	@GetMapping("/Data/PNHelper")
	public String getPNs(@RequestParam(value = "PnOrPnRoot", required = false, defaultValue = "%%") String pnOrPnRoot,
            @RequestParam(value = "Count", required = false, defaultValue = "50") Integer count) {
		return pnService.findPnByStartWith(pnOrPnRoot,count).toString();
	}
		
	@GetMapping("/Data/PNsInFamily")
	public String getPNsInFamily(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findAllPnByPnRoot(pnRoot).toString();
	}

	@GetMapping("/Data/SalesHistory")
	public String getSalesHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findSalesHistoryByPnRoot(pnRoot, 0).toString();
	}

	@GetMapping("/Data/QuoteHistory")
	public String getQuoteHistory(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findQuoteHistoryByPnRoot(pnRoot, 0).toString();
	}

	@GetMapping("/Data/DeliveryDuration")
	public String getDeliveryDuration(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findDeliveryDurationByPnRoot(pnRoot).toString();
	}

	@GetMapping("/Data/InventoryStock")
	public String getInventoryStock(
			@RequestParam(value = "PnRoot", required = false, defaultValue = "NULL") String pnRoot) {
		return pnService.findStockInfoByPnRoot(pnRoot).toString();
	}

	@GetMapping("/Data/AttachmentPath")
	public String getAttachmentPath(@RequestParam(value = "Pn", required = false, defaultValue = "NULL") String pn) {
		if (pn.equals("NULL")) {
			return "[]";
		}

		String attachmentPath = Utils.isWin() ? attachmentPathWindows : attachmentPathLinux;

		// change / \ * ? to -
		pn = pn.replaceAll("(\\\\|\\*|\\/|\\?)", "-");

		// Stand parts
		JSONArray DesignStand = makeJsonAarry(pn, pn, 4, "Design",
				Utils.findFiles(attachmentPath + "/" + pn + "/Design"));
		JSONArray DrawingStand = makeJsonAarry(pn, pn, 5, "Drawing",
				Utils.findFiles(attachmentPath + "/" + pn + "/Drawing"));
		JSONArray TestingCertificateStand = makeJsonAarry(pn, pn, 6, "TestingCertificate",
				Utils.findFiles(attachmentPath + "/" + pn + "/TestingCertificate"));
		JSONArray DataSheetStand = makeJsonAarry(pn, pn, 8, "DataSheet",
				Utils.findFiles(attachmentPath + "/" + pn + "/DataSheet"));
		JSONArray ManualsStand = makeJsonAarry(pn, pn, 9, "Manual",
				Utils.findFiles(attachmentPath + "/" + pn + "/Manual"));
		JSONArray QulitySheetStand = makeJsonAarry(pn, pn, 12, "QulitySheet",
				Utils.findFiles(attachmentPath + "/" + pn + "/QulitySheet"));

		String shortpn = Utils.makeShortPn(pn);
		// Short parts
		JSONArray DesignShort = makeJsonAarry(pn, shortpn, 4, "Design",
				Utils.findFiles(attachmentPath + "/" + shortpn + "/Design"));
		JSONArray DrawingShort = makeJsonAarry(pn, shortpn, 5, "Drawing",
				Utils.findFiles(attachmentPath + "/" + shortpn + "/Drawing"));
		JSONArray TestingCertificateShort = makeJsonAarry(pn, shortpn, 6, "TestingCertificate",
				Utils.findFiles(attachmentPath + "/" + shortpn + "/TestingCertificate"));
		JSONArray DataSheetShort = makeJsonAarry(pn, shortpn, 8, "DataSheet",
				Utils.findFiles(attachmentPath + "/" + shortpn + "/DataSheet"));
		JSONArray ManualsShort = makeJsonAarry(pn, shortpn, 9, "Manual",
				Utils.findFiles(attachmentPath + "/" + shortpn + "/Manual"));
		JSONArray QulitySheetShort = makeJsonAarry(pn, shortpn, 12, "QulitySheet",
				Utils.findFiles(attachmentPath + "/" + shortpn + "/QulitySheet"));

		JSONArray all = new JSONArray();
		all.addAll(DesignStand);
		all.addAll(DrawingStand);
		all.addAll(TestingCertificateStand);
		all.addAll(DataSheetStand);
		all.addAll(ManualsStand);
		all.addAll(QulitySheetStand);

		all.addAll(DesignShort);
		all.addAll(DrawingShort);
		all.addAll(TestingCertificateShort);
		all.addAll(DataSheetShort);
		all.addAll(ManualsShort);
		all.addAll(QulitySheetShort);

		return all.toJSONString();
	}

	private JSONArray makeJsonAarry(String pn, String folder, int cat, String catStr, String[] files) {
		JSONArray arr = new JSONArray();

		for (int i = 0, l = files.length; i < l; i++) {
			JSONObject obj = new JSONObject();
			obj.put("PN", pn);
			obj.put("Cat", cat);
			obj.put("Path", "/File/" + folder + "/" + catStr + "/" + files[i]);
			obj.put("File", files[i]);
			obj.put("DocType", Utils.getFileExt(files[i]));

			arr.add(obj);
		}
		return arr;
	}

}
