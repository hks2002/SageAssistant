package sageassistant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class ReportControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testHandReportRequestCOC() throws Exception {
        // *****************************************
        // Important: must add servletPath, if your serverlet need using servletPath
        // variable.
        // *****************************************
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/COC/showPdf?ProjectNO=ZCC190001-2")
                        .servletPath("/Report/COC/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/COC/exportWord?ProjectNO=ZCC190001-2")
                        .servletPath("/Report/COC/exportWord"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/rtf"));
    }

    @Test
    void testHandReportRequestDelivery() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/Delivery/showPdf?DeliveryNO=ZBL190001")
                        .servletPath("/Report/Delivery/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));

    }

    @Test
    void testHandReportRequestInvoice() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/Invoice/showPdf?InvoiceNO=ZFC1901001")
                        .servletPath("/Report/Invoice/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));
    }

    @Test
    void testHandReportRequestInvoice2() throws Exception {   
         this.mockMvc
         .perform(MockMvcRequestBuilders.get("/Report/Invoice2/showPdf?OrderNO=ZCC180020")
         .servletPath("/Report/Invoice2/showPdf"))
         .andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));
    }

    @Test
    void testHandReportRequestPurchaseOrder() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/Report/PurchaseOrder/showPdf?PurchaseNO=HCF2100015&TaxInclude=true")
                        .servletPath("/Report/PurchaseOrder/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/Report/PurchaseOrder/showPdf?PurchaseNO=ZXXXXX&TaxInclude=true")
                        .servletPath("/Report/PurchaseOrder/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("<H1>PurchaseNO:ZXXXXX not found!</H1>"));
    }

    @Test
    void testHandReportRequestReceipt() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/Receipt/showPdf?ReceiptNO=ZRA1900607")
                        .servletPath("/Report/Receipt/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));
    }

    //@Test
    void testHandReportRequestReceipt2() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get(
                        "/Report/Receipt2/showPdf?PurchaseSite=ZHU&VendorCode=20715&StartDay=20190101&EndDay=20200105")
                        .servletPath("/Report/Receipt2/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));
    }

    @Test
    void testHandReportRequestWorkOrder() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/WorkOrder/showPdf?ProjectOrWorkOrderNO=ZOF20000254")
                        .servletPath("/Report/WorkOrder/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/WorkOrder/showPdf?ProjectOrWorkOrderNO=HCC200033-1")
                        .servletPath("/Report/WorkOrder/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));
    }

    @Test
    void testHandReportRequestSOA() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/Report/SOA/showPdf?Site=ZHU&BPCode=00870")
                        .servletPath("/Report/SOA/showPdf"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/pdf"));
    }
}
