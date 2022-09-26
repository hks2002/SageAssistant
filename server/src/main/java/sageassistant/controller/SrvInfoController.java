/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 15:19:24
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:46:30
 * @FilePath       : \server\src\main\java\sageassistant\controller\SrvInfoController.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SrvInfoController {

    @Value("${project.name}")
    private String name;

    @Value("${project.version}")
    private String version;

    @Value("${spring.boot.version}")
    private String springBootVersion;

    @Value("${project.dependencies}")
    private String dependencies;

    @GetMapping("/Data/SrvVersion")
    public String getVersion() {
        return version;
    }

    @GetMapping("/Data/SrvName")
    public String getName() {
        return name;
    }

    @GetMapping("/Data/SrvInfo")
    public String getInfo() {
        return (
            "{\"name\":\"" +
            name +
            "\",\"version\":\"" +
            version +
            "\",\"springBootVersion\":\"" +
            springBootVersion +
            "\"}"
        );
    }

    @GetMapping("/Data/SrvProjectDependencies")
    public String getDependencies() {
        String str = "";
        str = dependencies.replace("Dependency", "");
        str = str.replaceAll("=", ":");
        str = str.replaceAll(":([^,}]+)", ":\"$1\"");
        JSONArray json = JSONArray.parseArray(str);
        return json.toString();
    }
}
