package sageassistant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportPOController extends ReportBaseController {
	private static final Logger log = LoggerFactory.getLogger(ReportPOController.class);
	
	public ReportPOController() {
		rpt = "reports/PO.rpt";
	}

	private void handingRequest(HttpServletRequest request, HttpServletResponse response, String action) {
		String PurchaseNO = request.getParameter("PurchaseNO");
		Boolean TaxInclude = Boolean.parseBoolean(request.getParameter("TaxInclude"));

		if (Utils.isNullOrEmpty(PurchaseNO)) {
			PurchaseNO = "NULL";
		}

		prepareReport();

		try {
			CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "PurchaseNO", PurchaseNO);
			CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "TaxInclude", TaxInclude);
			reportClientDocument.getReportDocument().getSummaryInfo().setTitle(PurchaseNO);
		} catch (ReportSDKExceptionBase e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		}

		doAction(response, action);
	}

	@GetMapping("/Report/PurchaseOrder/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "showPdf");
	}

	@GetMapping("/Report/PurchaseOrder/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportPdf");
	}

	@GetMapping("/Report/PurchaseOrder/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportWord");
	}

}