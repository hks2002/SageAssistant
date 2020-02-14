package sageassistant;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportCOCController extends ReportBaseController {
	public ReportCOCController() {
		rpt = "reports/COC.rpt";
	}

	private void handingRequest(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {
		openReport();

		String ProjectNO = request.getParameter("ProjectNO");
		Boolean CustomerOri = Boolean.parseBoolean(request.getParameter("CustomerOri"));

		logger.debug("exportPdf : ProjectNO : " + ProjectNO + "  " + "CustomerOri : " + CustomerOri);

		if (ProjectNO == null || ProjectNO.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Project NO can not be empty!");
			return;
		}

		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "ProjectNO", ProjectNO);
		CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "CustomerOri", CustomerOri);
		reportClientDocument.getReportDocument().getSummaryInfo().setTitle("COC" + ProjectNO);
	}

	@GetMapping("Report/COC/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		handingRequest(request, response);

		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, false);
	}

	@GetMapping("Report/COC/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		handingRequest(request, response);

		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportPDF(reportClientDocument, response, true);
	}

	@GetMapping("Report/COC/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response)
			throws ReportSDKExceptionBase, IOException {

		handingRequest(request, response);

		// This will be used by the viewer to display the desired report. True to
		// Download this report.
		CRJavaHelper.exportRTF(reportClientDocument, response, true);
	}
}
