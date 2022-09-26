/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-27 00:22:49
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:46:18
 * @FilePath       : \server\src\main\java\sageassistant\controller\ReportController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.ReportService;

@CrossOrigin
@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/Report/*/*")
    public void handReportRequest(HttpServletRequest request, HttpServletResponse response) {
        reportService.handRequest(request, response);
    }
}
