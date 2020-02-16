package sageassistant;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.iapi.util.ByteArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReportDeliveryController extends ReportBaseController {
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

	@GetMapping("Report/Delivery/showPdf")
	public void showPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "showPdf");
	}

	@GetMapping("Report/Delivery/exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportPdf");
	}

	@GetMapping("Report/Delivery/exportWord")
	public void exportWord(HttpServletRequest request, HttpServletResponse response) {
		handingRequest(request, response, "exportWord");
	}
}
