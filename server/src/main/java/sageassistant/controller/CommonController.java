/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 18:04:04
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:45:44
 * @FilePath       : \server\src\main\java\sageassistant\controller\CommonController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sageassistant.service.CommonService;

@CrossOrigin
@RestController
public class CommonController {

    @Autowired
    CommonService commonService;

    @GetMapping("/Data/Sites")
    public String getAllSites() {
        return commonService.getAllSites();
    }
}
