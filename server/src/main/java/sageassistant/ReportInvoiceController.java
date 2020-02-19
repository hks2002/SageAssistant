package sageassistant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportInvoiceController extends ReportBaseController {
	private static final Logger log = LoggerFactory.getLogger(ReportInvoiceController.class);
	
	public ReportInvoiceController() {
		rpt = "reports/Invoice.rpt";
	}

	private void handingRequest(HttpServletRequest request, HttpServletResponse response, String action) {
		String InvoiceNO = request.getParameter("InvoiceNO");

		if (Utils.isNullOrEmpty(InvoiceNO)) {
			InvoiceNO = "NULL";
		}

		prepareReport();

		try {
			CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "InvoiceNO", InvoiceNO);
			reportClientDocument.getReportDocument().getSummaryInfo().setTitle(InvoiceNO);
		} catch (ReportSDKExceptionBase e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		}

		doAction(response, action);
	}

	@GetMapping("/Report/Invoice/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "showPdf");
	}

	@GetMapping("/Report/Invoice/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportPdf");
	}

	@GetMapping("/Report/Invoice/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportWord");
	}
}
