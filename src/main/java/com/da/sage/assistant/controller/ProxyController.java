/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-04-03 23:29:09                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:36                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/ProxyController.java                      *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.alibaba.fastjson.JSONObject;
import com.da.sage.assistant.service.HttpService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private static final Logger log = LogManager.getLogger();

    @RequestMapping("/FastSage/**")
    public void handRequest(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        // url
        String schemas = request.getScheme();
        String server = request.getServerName();
        String method = request.getMethod();
        String uri = request.getRequestURI().replaceFirst("FastSage/", "");
        String query = request.getQueryString();
        String url = query == null ? "https://srvsyr01" + uri : "https://srvsyr01" + uri + "?" + query;

        // data in body
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        String data = sb.toString();
        // body is null, need build data from url query parts
        if (method.equals("POST") && data.isEmpty()) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            String jsonStr = JSONObject.toJSONString(parameterMap);
            JSONObject obj = JSONObject.parseObject(jsonStr);
            data = obj.toJSONString();
        }
        log.debug(data);

        // request header
        Map<String, String> requestHeaderMap = new HashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);

            log.debug("{} {}", name, value);
            switch (name) {
                case "origin":
                    value = "https://srvsyr01";
                    requestHeaderMap.put(name, value);
                    break;
                case "referer":
                    value = "https://srvsyr01" + value.substring(value.indexOf('/', 9));
                    requestHeaderMap.put(name, value);
                    break;
                case "accept-encoding":
                    // don't zip content
                    break;
                default:
                    requestHeaderMap.put(name, value);
            }
        }
        // requestHeaderMap.put("authorization", "Basic cmh1YW5nOkRhekAyMDIyMDMwMg==");
        // do request
        HttpResponse<String> resp = HttpService.proxy(method, url, requestHeaderMap, data);

        if (resp != null) {
            Map<String, List<String>> responseHeaderMap = resp.headers().map();
            responseHeaderMap.forEach((key, value) -> {
                log.debug("{} {}", key, value);
                value.forEach(val -> response.addHeader(key, val));
            });

            response.setStatus(resp.statusCode());
            response.setContentType(resp.headers().firstValue("content-type").orElse("text/html;charset=utf-8"));
            response.setCharacterEncoding("utf-8");
            // the response data contains some url, need change it to proxy server
            // ToDo: 💔 which path should and which path shouldn't
            if (uri.startsWith("/sdata/syracuse/collaboration/syracuse") || uri.contains("requestSvc")) {
                response
                    .getWriter()
                    .write(
                        resp.body().replace("https://srvsyr01", schemas + "://" + server).replace("srvsyr01", server)
                    );
            } else {
                response.getWriter().write(resp.body());
            }
        } else {
            response.setStatus(500);
        }
    }
}
