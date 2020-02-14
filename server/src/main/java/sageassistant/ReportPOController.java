package sageassistant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportPOController extends ReportBaseController {
	public ReportPOController() {
		rpt = "reports/PO.rpt";
	}
	
	private void handingRequest(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
	
			openReport();

		String PurchaseNO = request.getParameter("PurchaseNO");
		Boolean TaxInclude = Boolean.parseBoolean(request.getParameter("TaxInclude"));

		logger.debug("showPdf : PurchaseNO : " + PurchaseNO + "  " + "TaxInclude : " + TaxInclude);

		if (PurchaseNO == null || PurchaseNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Puchase NO can not be empty!");
			return;
		}

		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "PurchaseNO", PurchaseNO);
		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "TaxInclude", TaxInclude);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(PurchaseNO);
	}
	
	@GetMapping("Report/PurchaseOrder/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, false);

	}

	@GetMapping("Report/PurchaseOrder/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
		
		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, true);
	}

	@GetMapping("Report/PurchaseOrder/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
		
		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportRTF(reportClientDocument, response, true);
	}

}