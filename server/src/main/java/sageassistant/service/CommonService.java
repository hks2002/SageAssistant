/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:44:27
 * @FilePath       : \server\src\main\java\sageassistant\service\CommonService.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.service;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sageassistant.dao.CommonMapper;

@Service
public class CommonService {

    //private static final Logger log = LogManager.getLogger();

    @Autowired
    private CommonMapper commonMapper;

    public String getAllSites() {
        String s = "[";
        for (String o : commonMapper.getAllSites()) {
            s += "\"" + o + "\",";
        }
        if (commonMapper.getAllSites().size() > 0) {
            s = s.substring(0, s.lastIndexOf(','));
        }
        s += "]";
        return s;
    }
}
