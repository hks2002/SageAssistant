/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 18:04:00                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 21:47:25                                                                      *
 * @FilePath              : src/main/java/com/da/sage/assistant/controller/CommonController.java                     *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.controller;

import com.alibaba.fastjson.JSONArray;
import com.da.sage.assistant.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CommonController {

    @Autowired
    CommonService commonService;

    @GetMapping("/Data/Sites")
    public String getAllSites() {
        return JSONArray.toJSONString(commonService.getAllSites());
    }
}
