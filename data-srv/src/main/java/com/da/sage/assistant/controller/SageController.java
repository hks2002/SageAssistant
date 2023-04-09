/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-25 15:19:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:56                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/SageController.java                       *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.da.sage.assistant.service.SageService;
import com.da.sage.assistant.utils.SageActionHelper;
import com.da.sage.assistant.utils.SageActionHelper.MsgTyp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SageController {

    public String missingAuth() {
        return SageActionHelper.rtnObj(false, MsgTyp.ERROR, "Authorization is required.").toJSONString();
    }

    public String paraRequired(String name) {
        return SageActionHelper.rtnObj(false, MsgTyp.ERROR, name + " is required.").toJSONString();
    }

    @GetMapping("/Data/Login")
    public String doLogin(@RequestHeader(value = "authorization", required = false) String Auth) {
        if (Auth == null) {
            return missingAuth();
        }

        return SageService.doLogin(Auth).toJSONString();
    }

    @GetMapping("/Data/Profile")
    public String getProfile(@RequestHeader(value = "authorization", required = false) String Auth) {
        if (Auth == null) {
            return missingAuth();
        }

        return SageService.getProfile(Auth).toJSONString();
    }

    @GetMapping("/Data/Function")
    public String getFunction(@RequestHeader(value = "authorization", required = false) String Auth) {
        if (Auth == null) {
            return missingAuth();
        }

        return SageService.getFunction(Auth).toJSONString();
    }

    @GetMapping("/Data/PrintUUID")
    public String getPrintUUID(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "Report", required = false) String Report,
        @RequestParam(value = "ReportNO", required = false) String ReportNO,
        @RequestParam(value = "Opt", required = false, defaultValue = "") String Opt
    ) {
        if (Auth == null) {
            return missingAuth();
        }

        if (SageActionHelper.getFunction(Report) == null) {
            return SageActionHelper.rtnObj(false, MsgTyp.WARN, "Report is not supported.").toJSONString();
        }

        if (ReportNO == null) {
            return paraRequired("ReportNO");
        }

        return SageService.getReportUUID(Auth, Report, ReportNO, Opt).toJSONString();
    }

    @GetMapping("/Data/SageSADReady")
    public String updateSageSADReady(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Ready", required = false) String Ready
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        if (Ready == null) {
            return paraRequired("Ready");
        }

        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA51", Ready).toJSONString();
    }

    @GetMapping("/Data/SageDeliveryReady")
    public String updateSageDeliveryReady(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Ready", required = false) String Ready
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        if (Ready == null) {
            return paraRequired("Ready");
        }
        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA52", Ready).toJSONString();
    }

    @GetMapping("/Data/SageProductReady")
    public String updateSageProductReady(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Ready", required = false) String Ready
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        if (Ready == null) {
            return paraRequired("Ready");
        }
        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA54", Ready).toJSONString();
    }

    @GetMapping("/Data/SageProjectStatus")
    public String updateSageProjectStatus(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Status", required = false) String Status
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA72", Status).toJSONString();
    }

    @GetMapping("/Data/SageProjectBlockReason")
    public String updateSageProjectBlockReason(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Reason", required = false) String Reason
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA73", Reason).toJSONString();
    }

    @GetMapping("/Data/SageProjectComment")
    public String updateSageProjectComment(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Comment", required = false) String Comment
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA77", Comment).toJSONString();
    }

    @GetMapping("/Data/SageProjectAction")
    public String updateSageProjectAction(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Comment", required = false) String Comment
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA78", Comment).toJSONString();
    }

    @GetMapping("/Data/SageDeliveryPlanDate")
    public String updateSageDeliveryPlanDate(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "PlanDate", required = false) String PlanDate
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        return SageService.updateSageField(Auth, "SalesOrder", OrderNO, Line, "EA29", PlanDate).toJSONString();
    }

    @GetMapping("/Data/SagePurchaseAckDate")
    public String updateSagePurchaseAckDate(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "AckDate", required = false) String AckDate
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        return SageService.updateSageField(Auth, "PurchaseOrder", OrderNO, Line, "CA66", AckDate).toJSONString();
    }

    @GetMapping("/Data/SagePurchaseComment")
    public String updateSagePurchaseComment(
        @RequestHeader(value = "authorization", required = false) String Auth,
        @RequestParam(value = "OrderNO", required = false) String OrderNO,
        @RequestParam(value = "Line", required = false) Integer Line,
        @RequestParam(value = "Comment", required = false) String Comment
    ) {
        if (Auth == null) {
            return missingAuth();
        }
        if (OrderNO == null) {
            return paraRequired("OrderNO");
        }
        if (Line == null) {
            return paraRequired("Line");
        }
        return SageService.updateSageField(Auth, "PurchaseOrder", OrderNO, Line, "CA68", Comment).toJSONString();
    }
}
