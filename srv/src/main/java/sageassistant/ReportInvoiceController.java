package sageassistant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportInvoiceController {
	private static final Logger logger = LoggerFactory.getLogger(ReportInvoiceController.class);
	private ReportClientDocument reportClientDocument = new ReportClientDocument();

	@Autowired
	ReportDBConfig rptCfg;

	private void openReport() throws ReportSDKException {
		reportClientDocument = new ReportClientDocument();
		reportClientDocument.open("reports/Invoice.rpt", 0);
		CRJavaHelper.changeDataSource(reportClientDocument, rptCfg.getUsername(), rptCfg.getPassword(), rptCfg.getUrl(),
				rptCfg.getDriveClassName(), null);

	}

	@GetMapping("Report/Invoice/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		if (!reportClientDocument.isOpen()) {
			openReport();
		}

		String InvoiceNO = request.getParameter("InvoiceNO");

		logger.debug("showPdf : InvoiceNO : " + InvoiceNO);

		if (InvoiceNO == null || InvoiceNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invoice NO can not be empty!");
			return;
		}
		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "InvoiceNO", InvoiceNO);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(InvoiceNO);
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, false);
	}

	@GetMapping("Report/Invoice/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		if (!reportClientDocument.isOpen()) {
			openReport();
		}

		String InvoiceNO = request.getParameter("InvoiceNO");

		logger.debug("exportPdf : InvoiceNO : " + InvoiceNO);

		if (InvoiceNO == null || InvoiceNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invoice NO can not be empty!");
			return;
		}

		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "InvoiceNO", InvoiceNO);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(InvoiceNO);
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, true);
	}

	@GetMapping("Report/Invoice/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		if (!reportClientDocument.isOpen()) {
			openReport();
		}

		String InvoiceNO = request.getParameter("InvoiceNO");

		logger.debug("exportWord : InvoiceNO : " + InvoiceNO);

		if (InvoiceNO == null || InvoiceNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invoice NO can not be empty!");
			return;
		}

		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "InvoiceNO", InvoiceNO);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(InvoiceNO);
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportRTF(reportClientDocument, response, true);
	}
}
