package sageassistant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

@RestController
public class ReportDeliveryController extends ReportBaseController {
	private static final Logger log = LoggerFactory.getLogger(ReportDeliveryController.class);
	
	public ReportDeliveryController() {
		rpt = "reports/Delivery.rpt";
	}

	private void handingRequest(HttpServletRequest request, HttpServletResponse response, String action) {
		String DeliveryNO = request.getParameter("DeliveryNO");

		if (Utils.isNullOrEmpty(DeliveryNO)) {
			DeliveryNO = "NULL";
		}

		prepareReport();

		try {
			CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "DeliveryNO", DeliveryNO);
			reportClientDocument.getReportDocument().getSummaryInfo().setTitle(DeliveryNO);
		} catch (ReportSDKExceptionBase e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		}

		doAction(response, action);
	}

	@GetMapping("/Report/Delivery/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "showPdf");
	}

	@GetMapping("/Report/Delivery/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportPdf");
	}

	@GetMapping("/Report/Delivery/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportWord");
	}
}
