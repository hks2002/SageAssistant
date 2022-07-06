package sageassistant.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import sageassistant.model.Attachment;

@SpringBootTest
public class JsonStringTest {
    private static final Logger log = LogManager.getLogger(Utils.class);

    @Test
    public void nullValueTest() {
        Attachment obj = new Attachment();
        obj.setCat("cat");
        obj.setDocType("docType");
        obj.setFile(null);
        log.debug(obj.toString());
        Assertions.assertEquals(obj.toString(), "{\"Cat\":\"cat\",\"DocType\":\"docType\"}");
    }
}
