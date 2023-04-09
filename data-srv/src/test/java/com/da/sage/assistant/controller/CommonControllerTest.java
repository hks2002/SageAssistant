/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-03-15 23:49:52                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 22:19:04                                                                      *
 * @FilePath              : src/test/java/com/da/sage/assistant/controller/CommonControllerTest.java                 *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootTest
@AutoConfigureMockMvc
public class CommonControllerTest {

    static final Logger logger = LogManager.getLogger();

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAll() {
        RequestMappingHandlerMapping requestMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMapping.getHandlerMethods();

        List<String> testMethodsSkip = new ArrayList<>();
        testMethodsSkip.add("handleFileUpload");
        testMethodsSkip.add("handleFileDelete");
        testMethodsSkip.add("getAttachmentPath");
        testMethodsSkip.add("getCurrencyRateBatch");

        Set<RequestMappingInfo> mappingInfo = handlerMethodMap.keySet();
        for (RequestMappingInfo info : mappingInfo) {
            HandlerMethod method = handlerMethodMap.get(info);

            RequestMapping methodAnnotation = method.getMethodAnnotation(RequestMapping.class);
            MethodParameter[] params = method.getMethodParameters();
            String funName = method.getMethod().getName();

            // path[] method[]
            for (int i = 0; i < methodAnnotation.path().length && i < methodAnnotation.method().length; i++) {
                RequestMethod requestMethod = methodAnnotation.method()[0];
                String requestPath = methodAnnotation.path()[0];
                logger.info(requestMethod + ":" + requestPath);

                // make the request parameters string
                String reqParamsStr = "";
                for (MethodParameter param : params) {
                    if (param.hasParameterAnnotation(RequestParam.class)) {
                        String paramName = param.getParameterAnnotation(RequestParam.class).name();
                        logger.debug(paramName);
                        switch (paramName) {
                            case "Site":
                                reqParamsStr += paramName + "=" + "ZHU" + "&";
                                break;
                            case "PN":
                                reqParamsStr += paramName + "=" + "956A1001G01" + "&";
                                break;
                            case "Pn":
                                reqParamsStr += paramName + "=" + "956A1001_A" + "&";
                                break;
                            case "PnRoot":
                                reqParamsStr += paramName + "=" + "956A1001G01" + "&";
                                break;
                            case "DateFrom":
                                reqParamsStr += paramName + "=" + "2022-01-01" + "&";
                                break;
                            case "DateTo":
                                reqParamsStr += paramName + "=" + "2022-01-07" + "&";
                                break;
                            case "Limit":
                                reqParamsStr += paramName + "=" + "1" + "&";
                                break;
                            case "LastN":
                                reqParamsStr += paramName + "=" + "1" + "&";
                                break;
                            case "Count":
                                reqParamsStr += paramName + "=" + "1" + "&";
                                break;
                            case "Target":
                                reqParamsStr += paramName + "=" + "NetPrice" + "&";
                                break;
                            case "Currency":
                                reqParamsStr += paramName + "=" + "USD" + "&";
                                break;
                            case "Sour":
                                reqParamsStr += paramName + "=" + "RMB" + "&";
                                break;
                            case "Dest":
                                reqParamsStr += paramName + "=" + "RMB" + "&";
                                break;
                            case "Date":
                                reqParamsStr += paramName + "=" + "2022-01-01" + "&";
                                break;
                            case "Year":
                                reqParamsStr += paramName + "=" + "2022" + "&";
                                break;
                            case "AccountNO":
                                reqParamsStr += paramName + "=" + "1002210" + "&";
                                break;
                            case "CustomerCode":
                                reqParamsStr += paramName + "=" + "00870" + "&";
                                break;
                            case "SupplierCode":
                                reqParamsStr += paramName + "=" + "00870" + "&";
                                break;
                            case "FaPiao":
                                reqParamsStr += paramName + "=" + "NULL" + "&";
                            case "InvoiceNO":
                                reqParamsStr += paramName + "=" + "NULL" + "&";
                            default:
                        }
                    }
                }
                if (reqParamsStr.length() > 0) {
                    reqParamsStr = "?" + reqParamsStr.substring(0, reqParamsStr.length() - 1);
                }
                logger.info(reqParamsStr);

                // Do request
                switch (requestMethod) {
                    case GET:
                        if (!testMethodsSkip.contains(funName)) {
                            try {
                                mockMvc
                                    .perform(MockMvcRequestBuilders.get(requestPath + reqParamsStr))
                                    .andExpect(MockMvcResultMatchers.status().isOk());
                            } catch (Exception e) {
                                logger.error(e);
                            }
                        }

                        break;
                    default:
                }
            }
        }
    }
}
