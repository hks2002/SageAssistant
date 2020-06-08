package sageassistant.service;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;

import sageassistant.utils.CRJavaHelper;

/*************************************************************
 * Important, Must remove deploy folder, then update the War file
 ************************************************************/

@Service
public class ReportService {
	private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    private String path="";
    
	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassNname;

	/*
	 * request url like /Report/COC/showPdf
	 */
	public void handRequest(HttpServletRequest request, HttpServletResponse response) {

		String report = request.getServletPath().split("/")[2];
		String action = request.getServletPath().split("/")[3];
		Map<String, String[]> requestParams = request.getParameterMap();
		String reportTitle = "";
		
		/*
		 * Define it at class, could improve the request performance,
		 * But, you must reload SageAssistantSrv each time you update your deployed war package.
		 */
		 ReportClientDocument reportClientDocument = new ReportClientDocument();
			
		try {
			//if (reportClientDocument ==null || !reportClientDocument.isOpen()) {	
				
				path = "reports/" + report + ".rpt";
				reportClientDocument.open(path, 0);
				
			//}
			CRJavaHelper.changeDataSource(reportClientDocument, username, password, url, driverClassNname, "SAGE");

			reportClientDocument.getReportDocument().getSummaryInfo().setTitle("");
			for (String key : requestParams.keySet()) {
				String val = requestParams.get(key)[0];
				CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", key, val);
				reportTitle = reportTitle + val;
			}
			if (report.equals("COC")) {
				reportTitle = "COC" + reportTitle;
			}
			reportClientDocument.getReportDocument().getSummaryInfo().setTitle(reportTitle);

			if (action.equals("showPdf")) {
				CRJavaHelper.exportPDF(reportClientDocument, response, false);
			} else if (action.equals("exportPdf")) {
				CRJavaHelper.exportPDF(reportClientDocument, response, true);
			} else if (action.equals("exportWord")) {
				CRJavaHelper.exportRTF(reportClientDocument, response, true);
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Only support showPdf,exportPdf,exportWord");
			}

			 reportClientDocument.close();

		} catch (IOException | ReportSDKExceptionBase e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			try {
				response.getWriter().write("<H1>Handle report error!</H1>");
			} catch (IOException e1) {
				log.error(e1.getMessage());
			}
			log.error(e.getMessage());
		}

	}

}
