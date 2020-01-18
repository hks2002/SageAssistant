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
public class ReportPOController {
	private static final Logger logger = LoggerFactory.getLogger(ReportPOController.class);

	private ReportClientDocument reportClientDocument = new ReportClientDocument();

	@Autowired
	ReportDBConfig rptCfg;

	private void openReport() throws ReportSDKException {
		reportClientDocument = new ReportClientDocument();
		reportClientDocument.open("reports/PO.rpt", 0);
		CRJavaHelper.changeDataSource(reportClientDocument, rptCfg.getUsername(), rptCfg.getPassword(), rptCfg.getUrl(),
				rptCfg.getDriveClassName(), null);

	}

	@GetMapping("Report/PurchaseOrder/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		if (!reportClientDocument.isOpen()) {
			openReport();
		}

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
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, false);

	}

	@GetMapping("Report/PurchaseOrder/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		if (!reportClientDocument.isOpen()) {
			openReport();
		}

		String PurchaseNO = request.getParameter("PurchaseNO");
		Boolean TaxInclude = Boolean.parseBoolean(request.getParameter("TaxInclude"));

		logger.debug("exportPdf : PurchaseNO : " + PurchaseNO + "  " + "TaxInclude : " + TaxInclude);

		if (PurchaseNO == null || PurchaseNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Puchase NO can not be empty!");
			return;
		}

		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "PurchaseNO", PurchaseNO);
		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "TaxInclude", TaxInclude);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(PurchaseNO);
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, true);
	}

	@GetMapping("Report/PurchaseOrder/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		if (!reportClientDocument.isOpen()) {
			openReport();
		}

		String PurchaseNO = request.getParameter("PurchaseNO");
		Boolean TaxInclude = Boolean.parseBoolean(request.getParameter("TaxInclude"));

		logger.debug("exportWord : PurchaseNO : " + PurchaseNO + "  " + "TaxInclude : " + TaxInclude);

		if (PurchaseNO == null || PurchaseNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Puchase NO can not be empty!");
			return;
		}

		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "PurchaseNO", PurchaseNO);
		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "TaxInclude", TaxInclude);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(PurchaseNO);
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportRTF(reportClientDocument, response, true);
	}

}