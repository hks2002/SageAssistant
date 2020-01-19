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
public class ReportDeliveryController {
	private static final Logger logger = LoggerFactory.getLogger(ReportDeliveryController.class);
	private static ReportClientDocument reportClientDocument = new ReportClientDocument();

	@Autowired
	ReportDBConfig rptCfg;

	private void openReport() throws ReportSDKException {
		reportClientDocument = new ReportClientDocument();
		reportClientDocument.open("reports/Delivery.rpt", 0);
		CRJavaHelper.changeDataSource(reportClientDocument, rptCfg.getUsername(), rptCfg.getPassword(), rptCfg.getUrl(),
				rptCfg.getDriveClassName(), null);

	}
	
	private void handingRequest(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
		if (!reportClientDocument.isOpen()) {
			openReport();
		}

		String DeliveryNO = request.getParameter("DeliveryNO");

		logger.debug("showPdf : DeliveryNO : " + DeliveryNO);

		if (DeliveryNO == null || DeliveryNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Delivery NO can not be empty!");
			return;
		}
		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "DeliveryNO", DeliveryNO);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle(DeliveryNO);
	}
	
	@GetMapping("Report/Delivery/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, false);
	}

	@GetMapping("Report/Delivery/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, true);
	}

	@GetMapping("Report/Delivery/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
		
		handingRequest(request, response);
		
		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportRTF(reportClientDocument, response, true);
	}
}
