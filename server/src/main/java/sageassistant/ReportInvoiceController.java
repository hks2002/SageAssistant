package sageassistant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReportInvoiceController extends ReportBaseController {
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

	@GetMapping("Report/Invoice/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "showPdf");
	}

	@GetMapping("Report/Invoice/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportPdf");
	}

	@GetMapping("Report/Invoice/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportWord");
	}
}
