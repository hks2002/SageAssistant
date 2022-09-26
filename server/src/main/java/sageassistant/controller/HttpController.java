/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 23:29:52
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:46:05
 * @FilePath       : \server\src\main\java\sageassistant\controller\HttpController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.HttpService;

@RestController
public class HttpController {

    @Autowired
    HttpService httpService;

    @RequestMapping("/Proxy/**/*")
    public void doRequest(HttpServletRequest request, HttpServletResponse response) {
        httpService.doRequest(request, response);
    }
}
