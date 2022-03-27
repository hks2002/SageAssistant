package sageassistant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsTest {
    private static final Logger log = LogManager.getLogger(Utils.class);

    @Test
    public void testDateDiff() throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Date start = fmt.parse("2010-01-02");
        Date end = fmt.parse("2010-03-02");
        long diff = Utils.dateDiff(start, end);
        log.debug("diff:" + diff);
        Assertions.assertEquals(diff, 59);
    }

    @Test
    void testFormatDate() {
        Assertions.assertEquals("", Utils.formatDate(null));
        Assertions.assertTrue(Utils.formatDate(new Date()).matches("\\d{4}-\\d{2}-\\d{2}"));
        Assertions.assertFalse((new Date()).toString().matches("\\d{4}-\\d{2}-\\d{2}"));
        log.debug((new Date()).toString());
    }

    @Test
    public void testGetFileExt() {
        Assertions.assertEquals(Utils.getFileExt("filename.ext"), "EXT");
        Assertions.assertEquals(Utils.getFileExt("filename.ext.ext"), "EXT");
        Assertions.assertEquals(Utils.getFileExt("filename"), "");
    }

    @Test
    void testIsNullOrEmpty() {
        Assertions.assertTrue(Utils.isNullOrEmpty(""));
        Assertions.assertFalse(Utils.isNullOrEmpty(" "));
        Assertions.assertTrue(Utils.isNullOrEmpty(null));
    }

    @Test
    public void testIsServerAtZhuhai() {
        Assertions.assertTrue(Utils.isServerAtZhuhai());
    }

    @Test
    void testIsWin() {
        // run it in win
        Assertions.assertTrue(Utils.isWin());
        // Assertions.assertFalse(Utils.isWin());
    }

    @Test
    public void testMakeShortPn() {
        Assertions.assertEquals(Utils.makeShortPn("98A1234567890_CPD_P-11_D"), "98A12345678_D");

        Assertions.assertEquals(Utils.makeShortPn("98A1234567890_CPD-11_D"), "98A12345678_D");

        Assertions.assertEquals(Utils.makeShortPn("98A1234567890G01P01_NQ_D_-"), "98A12345678_D");

        Assertions.assertEquals(Utils.makeShortPn("98A1234567890G01P01NQ_D_-"), "98A12345678_D");

        Assertions.assertEquals(Utils.makeShortPn("9C12345G01P01NQ_D_-"), "9C12345_D");

        Assertions.assertEquals(Utils.makeShortPn("9C12345-67G01P01NQ_D_-"), "9C12345-67_D");

        Assertions.assertEquals(Utils.makeShortPn("9C12345G01P01NQ-11_D_-"), "9C12345_D");

        Assertions.assertEquals(Utils.makeShortPn("856A1234567890G01P01NQ_D"), "856A1234_D");

        Assertions.assertEquals(Utils.makeShortPn("RRT123456G01P01NQ_A_-"), "RRT123456_A");

        Assertions.assertEquals(Utils.makeShortPn("HU12345G01P01NQ_A_-"), "HU12345_A");

        Assertions.assertEquals(Utils.makeShortPn("330A12345678G01P01NQ_A_-"), "330A123456_A");

        Assertions.assertEquals(Utils.makeShortPn("9401M01G01P01NQ_A_-"), "9401M01_A");

        Assertions.assertEquals(Utils.makeShortPn("A12345-1_A_-"), "A12345_A");

        Assertions.assertEquals(Utils.makeShortPn("956A1001_QU_A"), "956A1001_A");
    }

    @Test
    public void testFindFiles() {
        String classLoaderPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
        log.debug(classLoaderPath);
        // maven doesn't copy the testresource, why?
        String fpath = classLoaderPath + "../../src/test/resources/sql";
        log.debug(fpath);
        Assertions.assertEquals(Utils.findFiles(fpath + "/XXXX").length, 0);
        Assertions.assertEquals(Utils.findFiles(fpath + "/test.sql").length, 0);
        Assertions.assertTrue(Utils.findFiles(fpath).length == 1);
        Assertions.assertTrue(Utils.findFiles(fpath)[0].equals("test.sql"));
    }

    @Test
    public void testReadFileContent() {
        String sql = Utils.readFileContent("../../src/test/resources/sql/test.sql");
        log.debug(sql);
        Assertions.assertEquals(sql, "This is test sql");
    }
}
