package sageassistant;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

public class ReportBaseController {
	private static final Logger log = LoggerFactory.getLogger(ReportBaseController.class);
	
	protected ReportClientDocument reportClientDocument = new ReportClientDocument();

	@Autowired
	private Environment env;

	protected String rpt;

	protected void openReport() {
		try {
			reportClientDocument.open(rpt, 0);
		} catch (ReportSDKException e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		}
		try {
			CRJavaHelper.changeDataSource(reportClientDocument, env.getProperty("spring.datasource.username"),
					env.getProperty("spring.datasource.password"), env.getProperty("spring.datasource.url"),
					env.getProperty("spring.datasource.driver-class-name"), null);
		} catch (ReportSDKException e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		}
	}

	protected void prepareReport() {
		try {
			if (!reportClientDocument.isOpen()) {
				openReport();
			}
		} catch (ReportSDKException e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		}

		try {
			// here could throw one exception, if document is timeout closed.
			reportClientDocument.getStatusVariables();
		} catch (ReportSDKException e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
			reportClientDocument = new ReportClientDocument();
			openReport();
		}

	}

	protected void doAction(HttpServletResponse response, String action) {
		try {
			if (action == "showPdf") {
				CRJavaHelper.exportPDF(reportClientDocument, response, false);
			} else if (action == "exportPdf") {
				CRJavaHelper.exportPDF(reportClientDocument, response, true);
			} else if (action == "exportWord") {
				CRJavaHelper.exportRTF(reportClientDocument, response, true);
			}
		} catch (ReportSDKExceptionBase e) {
			log.error("Code: " + e.errorCode() + e.getMessage());
		} catch ( IOException  e2) {
			log.error(e2.getMessage());
		}
	}
}
