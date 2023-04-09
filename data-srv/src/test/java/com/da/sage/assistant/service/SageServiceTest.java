/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2023-03-16 17:14:44                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-08 22:51:01                                                                      *
 * @FilePath              : src/test/java/com/da/sage/assistant/service/SageServiceTest.java                         *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.service;

import com.da.sage.assistant.utils.Utils;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SageServiceTest {

    private static final Logger log = LogManager.getLogger();

    private static String auth;

    @BeforeAll
    public static void loadAuth() {
        auth = Utils.readFileContent("auth.txt");
        log.info(auth);
    }

    @AfterEach
    public void tearDown() {
        log.info("do logout for each");
        SageService.doLogout(auth);
    }

    @Test
    void testDoLogin() {
        SageService.doLogin(auth);
    }

    @Test
    void testDoLogout() {
        SageService.doLogout(auth);
    }

    @Test
    void testEndSession() {
        SageService.endSession(auth, "c961d328-f1bb-449d-b305-5446d9423fe0");
    }

    @Test
    void testDoLoginDoLogout() {
        SageService.doLogin(auth);
        SageService.getSageSessionCache(auth, "GESSOH", "2~1");
        SageService.doLogout(auth);
    }

    @Test
    void testGetProfile() {
        SageService.getProfile(auth);
    }

    @Test
    void testGetFunction() {
        SageService.getFunction(auth);
    }

    @Test
    void testGetSageSession() throws ExecutionException {
        SageService.getSageSessionCache(auth, "GESSOH", "2~1");
    }

    @Test
    void testGetReportUUID() {
        SageService.getReportUUID(auth, "SalesOrder", "ZCC230010", null);
    }

    @Test
    void testUpdateField() {
        // SAD: EA55
        SageService.updateSageField(auth, "SalesOrder", "ZCC220007", 8, "EA55", "1");
        SageService.doLogout(auth);
    }
}
