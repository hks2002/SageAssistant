package sageassistant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

public class ReportBaseController {
	protected static final Logger logger = LoggerFactory.getLogger(ReportCOCController.class);
	protected ReportClientDocument reportClientDocument = new ReportClientDocument();

	protected String rpt;
	@Autowired
	ReportDBConfig rptCfg;

	protected void openReport() {

		try {
			if (reportClientDocument.isOpen() && reportClientDocument != null) {
				// here could throw one exception, if document is timeout closed.
				reportClientDocument.getStatusVariables();
			}
		} catch (ReportSDKException e) {
			logger.error("Code:" + e.errorCode() + e.getMessage());

			if (e.errorCode() == -2147215349) {// Code:-2147215349 Document is closed。
				reportClientDocument = null;
			}
		}

		try {
			if (!reportClientDocument.isOpen() || reportClientDocument == null) {
				reportClientDocument = new ReportClientDocument();
				reportClientDocument.open(rpt, 0);
				CRJavaHelper.changeDataSource(reportClientDocument, rptCfg.getUsername(), rptCfg.getPassword(),
						rptCfg.getUrl(), rptCfg.getDriveClassName(), null);
			}
		} catch (ReportSDKException e) {
			logger.error("Code:" + e.errorCode() + e.getMessage());
		}

	}
}
