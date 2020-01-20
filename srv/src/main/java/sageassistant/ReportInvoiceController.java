package sageassistant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportInvoiceController extends ReportBaseController {
	public ReportInvoiceController() {
		rpt = "reports/Invoice.rpt";
	}
	
	private void handingRequest(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
				openReport();

		String InvoiceNO = request.getParameter("InvoiceNO");

		logger.debug("showPdf : InvoiceNO : " + InvoiceNO);

		if (InvoiceNO == null || InvoiceNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invoice NO can not be empty!");
			return;
		}
		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "InvoiceNO", InvoiceNO);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(InvoiceNO);
	}
	
	@GetMapping("Report/Invoice/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
		
		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, false);
	}

	@GetMapping("Report/Invoice/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, true);
	}

	@GetMapping("Report/Invoice/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
		
		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportRTF(reportClientDocument, response, true);
	}
}
