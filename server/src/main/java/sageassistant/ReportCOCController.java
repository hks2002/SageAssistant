package sageassistant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportCOCController extends ReportBaseController {
	private static final Logger log = LoggerFactory.getLogger(ReportCOCController.class);
	
	public ReportCOCController() {
		rpt = "reports/COC.rpt";
	}

	private void handingRequest(HttpServletRequest request, HttpServletResponse response, String action) {
		String ProjectNO = request.getParameter("ProjectNO");
		Boolean CustomerOri = Boolean.parseBoolean(request.getParameter("CustomerOri"));

		if (Utils.isNullOrEmpty(ProjectNO)) {
			ProjectNO = "NULL";
		}
		prepareReport();

		try {
			CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "ProjectNO", ProjectNO);
			CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "CustomerOri", CustomerOri);
			reportClientDocument.getReportDocument().getSummaryInfo().setTitle("COC" + ProjectNO);
		} catch (ReportSDKExceptionBase e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		}

		doAction(response, action);
	}

	@GetMapping("/Report/COC/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "showPdf");
	}

	@GetMapping("/Report/COC/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportPdf");
	}

	@GetMapping("/Report/COC/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportWord");
	}
}
