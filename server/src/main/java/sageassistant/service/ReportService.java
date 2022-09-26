/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:45:05
 * @FilePath       : \server\src\main\java\sageassistant\service\ReportService.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.service;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sageassistant.dao.RptMapper;
import sageassistant.model.RptCOC;
import sageassistant.model.RptCOCSerialLot;
import sageassistant.model.RptInvoicePackage;
import sageassistant.model.RptInvoicePay;
import sageassistant.model.RptPurchaseTax;
import sageassistant.model.RptWorkOrder;
import sageassistant.model.RptWorkOrderComp;
import sageassistant.model.RptWorkOrderOpestt;
import sageassistant.model.RptWorkOrderSfichsui;
import sageassistant.utils.CRJavaHelper;

/*************************************************************
 * Important, Must remove deploy folder, then update the War file
 ************************************************************/

@Service
public class ReportService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private RptMapper rptMapper;

    /*
     * request url like /Report/COC/showPdf
     */
    public void handRequest(HttpServletRequest request, HttpServletResponse response) {
        String ProjectNO = null;
        String DeliveryNO = null;
        String PurchaseNO = null;
        String ReceiptNO = null;
        String PurchaseSite = null;
        String VendorCode = null;
        String StartDay = null;
        String EndDay = null;
        String InvoiceNO = null;
        String OrderNO = null;
        String ProjectOrWorkOrderNO = null;
        String TaxInclude = null;
        String MfgNO = null;
        String PN = null;
        List<?> list = null;
        List<RptInvoicePackage> InvoicePackage = null;
        List<RptInvoicePay> InvoicePay = null;

        try {
            String report = request.getServletPath().split("/")[2];
            String action = request.getServletPath().split("/")[3];
            ReportClientDocument reportClientDocument = new ReportClientDocument();

            switch (report) {
                case "COC":
                    reportClientDocument.open("reports/COC.rpt", 0);
                    log.info(reportClientDocument.getSubreportController().getSubreportNames().toString());

                    ProjectNO = request.getParameter("ProjectNO");
                    list = rptMapper.findCOCByProjectNO(ProjectNO);
                    if (list.size() == 0) {
                        response.getWriter().write("<H1>ProjectNO:" + ProjectNO + " not found!</H1>");
                        return;
                    }

                    PN = ((RptCOC) list.get(0)).getITMREF_0();

                    List<RptCOCSerialLot> COCSerLot = rptMapper.findCOCSerialLotByProjectNOAndPn(ProjectNO, PN);

                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptCOC", "");
                    CRJavaHelper.passPOJO(
                        reportClientDocument,
                        COCSerLot,
                        "sageassistant.model.RptCOCSerialLot",
                        "SerialLot.rpt"
                    );

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle("COC" + ProjectNO);
                    break;
                case "Delivery":
                    reportClientDocument.open("reports/Delivery.rpt", 0);

                    DeliveryNO = request.getParameter("DeliveryNO");
                    list = rptMapper.findDeliveryByDeliveryNO(DeliveryNO);
                    if (list.size() == 0) {
                        response.getWriter().write("<H1>DeliveryNO:" + DeliveryNO + " not found!</H1>");
                        return;
                    }
                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptDelivery", "");

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle(DeliveryNO);
                    break;
                case "Invoice":
                    reportClientDocument.open("reports/Invoice.rpt", 0);

                    InvoiceNO = request.getParameter("InvoiceNO");
                    list = rptMapper.findInvoiceByInvoiceNO(InvoiceNO);
                    if (list.size() == 0) {
                        response.getWriter().write("<H1>InvoiceNO:" + InvoiceNO + " not found!</H1>");
                        return;
                    }
                    InvoicePay = rptMapper.findInvoicePayByInvoiceNO(InvoiceNO);
                    InvoicePackage = rptMapper.findInvoicePackageByInvoiceNO(InvoiceNO);
                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptInvoice", "");
                    CRJavaHelper.passPOJO(
                        reportClientDocument,
                        InvoicePackage,
                        "sageassistant.model.RptInvoicePackage",
                        "Package"
                    );
                    CRJavaHelper.passPOJO(reportClientDocument, InvoicePay, "sageassistant.model.RptInvoicePay", "Pay");

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle(InvoiceNO);
                    break;
                case "Invoice2":
                    reportClientDocument.open("reports/Invoice2.rpt", 0);

                    OrderNO = request.getParameter("OrderNO");
                    list = rptMapper.findInvoice2ByOrderNO(OrderNO);
                    if (list.size() == 0) {
                        response.getWriter().write("<H1>Sales OrderNO:" + OrderNO + " not found!</H1>");
                        return;
                    }

                    InvoicePay = rptMapper.findInvoice2PayByOrderNO(OrderNO);

                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptInvoice2", "");
                    CRJavaHelper.passPOJO(reportClientDocument, InvoicePay, "sageassistant.model.RptInvoicePay", "Pay");

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle("IVC-" + OrderNO);
                    break;
                case "PurchaseOrder":
                    reportClientDocument.open("reports/PurchaseOrder.rpt", 0);

                    PurchaseNO = request.getParameter("PurchaseNO");
                    TaxInclude = request.getParameter("TaxInlude");
                    if (TaxInclude == null) {
                        TaxInclude = "true";
                    }

                    list = rptMapper.findPurchaseByPurchaseNO(PurchaseNO);
                    if (list.size() == 0) {
                        response.getWriter().write("<H1>PurchaseNO:" + PurchaseNO + " not found!</H1>");
                        return;
                    }
                    List<RptPurchaseTax> PurchaseTax = rptMapper.findPurchaseTaxByPurchaseNO(PurchaseNO);

                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptPurchase", "");
                    CRJavaHelper.passPOJO(
                        reportClientDocument,
                        PurchaseTax,
                        "sageassistant.model.RptPurchaseTax",
                        "TaxLine"
                    );

                    CRJavaHelper.addDiscreteParameterValue(reportClientDocument, "", "TaxInclude", TaxInclude);

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle(PurchaseNO);
                    break;
                case "Receipt":
                    reportClientDocument.open("reports/Receipt.rpt", 0);

                    ReceiptNO = request.getParameter("ReceiptNO");
                    list = rptMapper.findReceiptByReceiptNO(ReceiptNO);
                    if (list.size() == 0) {
                        response.getWriter().write("<H1>ReceiptNO:" + ReceiptNO + " not found!</H1>");
                        return;
                    }

                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptReceipt", "");

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle(ReceiptNO);
                    break;
                case "Receipt2":
                    reportClientDocument.open("reports/Receipt.rpt", 0);

                    PurchaseSite = request.getParameter("PurchaseSite");
                    VendorCode = request.getParameter("VendorCode");
                    StartDay = request.getParameter("StartDay");
                    EndDay = request.getParameter("EndDay");

                    try {
                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
                        LocalDate startDate = LocalDate.parse(StartDay, fmt);
                        LocalDate endDate = LocalDate.parse(EndDay, fmt);

                        DateTimeFormatter fmtsql = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        StartDay = startDate.format(fmtsql);
                        EndDay = endDate.format(fmtsql);
                    } catch (DateTimeParseException ex) {
                        response.getWriter().write("<H1>Date Format is not correct!</H1>");
                        return;
                    }

                    list =
                        rptMapper.findReceiptByPurchaseSiteVenderCodeDuration(
                            PurchaseSite,
                            VendorCode,
                            StartDay,
                            EndDay
                        );
                    if (list.size() == 0) {
                        response
                            .getWriter()
                            .write(
                                "<H1>PurchaseSite:" +
                                PurchaseSite +
                                " for vendor " +
                                VendorCode +
                                "bewteen " +
                                StartDay +
                                "--> " +
                                EndDay +
                                " not found!</H1>"
                            );
                        return;
                    }

                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptReceipt", "");

                    reportClientDocument
                        .getReportDocument()
                        .getSummaryInfo()
                        .setTitle(PurchaseSite + "-" + VendorCode + "-" + StartDay + "-" + EndDay);
                    break;
                case "WorkOrder":
                    reportClientDocument.open("reports/WorkOrder.rpt", 0);

                    ProjectOrWorkOrderNO = request.getParameter("ProjectOrWorkOrderNO");
                    list = rptMapper.findWorkOrderByProjectOrWorkOrderNO(ProjectOrWorkOrderNO);
                    if (list.size() == 0) {
                        response
                            .getWriter()
                            .write("<H1>ProjectOrWorkOrderNO:" + ProjectOrWorkOrderNO + " not found!</H1>");
                        return;
                    }
                    MfgNO = ((RptWorkOrder) list.get(0)).getMFGNUM_0();
                    List<RptWorkOrderComp> WorkOrderComp = rptMapper.findWorkOrderCompByMfgNO(MfgNO);
                    List<RptWorkOrderOpestt> WorkOrderOpestt = rptMapper.findWorkOrderOpesttByMfgNO(MfgNO);
                    List<RptWorkOrderSfichsui> WorkOrderSfichsui = rptMapper.findWorkOrderSfichsuiByMfgNO(MfgNO);

                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptWorkOrder", "");
                    CRJavaHelper.passPOJO(
                        reportClientDocument,
                        WorkOrderComp,
                        "sageassistant.model.RptWorkOrderComp",
                        "Comp"
                    );
                    CRJavaHelper.passPOJO(
                        reportClientDocument,
                        WorkOrderOpestt,
                        "sageassistant.model.RptWorkOrderOpestt",
                        "Opestt"
                    );
                    CRJavaHelper.passPOJO(
                        reportClientDocument,
                        WorkOrderSfichsui,
                        "sageassistant.model.RptWorkOrderSfichsui",
                        "Sfichsui"
                    );

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle(ProjectOrWorkOrderNO);
                    break;
                case "SOA":
                    reportClientDocument.open("reports/SOA.rpt", 0);

                    String Site = request.getParameter("Site");
                    String BPCode = request.getParameter("BPCode");
                    list = rptMapper.findSOABySiteAndBPCode(Site, BPCode);

                    log.info(list);
                    if (list.size() == 0) {
                        response.getWriter().write("<H1>Site:" + Site + " for Customer:" + BPCode + " not found!</H1>");
                        return;
                    }

                    CRJavaHelper.passPOJO(reportClientDocument, list, "sageassistant.model.RptSOA", "");

                    reportClientDocument.getReportDocument().getSummaryInfo().setTitle("SOA-" + Site + BPCode);
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("<H1>Report " + report + "not support yet!</H1>");
            }

            /* Change Author */
            reportClientDocument.getReportDocument().getSummaryInfo().setAuthor("SageAssistant");

            // Show Chinese font
            // response.setCharacterEncoding("UTF-8");

            /* Do report action */
            if ("showPdf".equals(action)) {
                CRJavaHelper.exportPDF(reportClientDocument, response, false);
            } else if ("exportPdf".equals(action)) {
                CRJavaHelper.exportPDF(reportClientDocument, response, true);
            } else if ("exportWord".equals(action)) {
                CRJavaHelper.exportRTF(reportClientDocument, response, true);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Only support showPdf,exportPdf,exportWord");
            }

            /* Close report */
            reportClientDocument.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.getWriter().write("<H1>Request Url Wrong!</H1>");
            } catch (IOException e1) {
                log.error(e1.getMessage());
            }
        } catch (IOException | ReportSDKExceptionBase | ClassNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.getWriter().write("<H1>Handle report error!</H1>");
            } catch (IOException e1) {
                log.error(e1.getMessage());
            }
            log.error(e.getMessage());
            //** Open it,  if want to debug report, it could output detail message, such ORM field error.**/
            e.printStackTrace();
        }
    }
}
