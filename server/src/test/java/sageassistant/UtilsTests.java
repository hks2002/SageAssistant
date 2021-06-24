package sageassistant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

import sageassistant.utils.Utils;

@SpringBootTest
public class UtilsTests {
	private static final Logger log = LogManager.getLogger(UtilsTests.class);
	
	@Test
	public void testReadFileContent() {
		String sql = Utils.readFileContent("sql/test.sql");
		log.debug(sql);
		Assertions.assertEquals(sql, "This is test sql");
	}
	
	@Test 
	public void testIsNullOrEmpty() {
		Assertions.assertTrue(Utils.isNullOrEmpty(null));
		Assertions.assertTrue(Utils.isNullOrEmpty(""));
	}
	
	@Test
	public void testGetFileExt() {
		Assertions.assertEquals(Utils.getFileExt("filename.ext"), "EXT");
		Assertions.assertEquals(Utils.getFileExt("filename.ext.ext"), "EXT");
		Assertions.assertEquals(Utils.getFileExt("filename"), "");
	}
	
	@Test
	public void testIsWin() {
		// run it in win
		Assertions.assertTrue(Utils.isWin());
		//Assertions.assertFalse(Utils.isWin());
	}
	
	@Test
	public void testIsServerAtZhuhai() {
		Assertions.assertTrue(Utils.isServerAtZhuhai());
	}
	
	@Test
	public void testFindFiles() {
		String fpath = Thread.currentThread().getContextClassLoader().getResource("sql").getPath();
		Assertions.assertEquals(Utils.findFiles(fpath + "/XXXX").length,0);
		Assertions.assertEquals(Utils.findFiles(fpath + "/test.sql").length,0);
		Assertions.assertTrue(Utils.findFiles(fpath).length==1);
		Assertions.assertTrue(Utils.findFiles(fpath)[0].equals("test.sql"));
	}
	
	
	@Test
	public void testMakeShortPn() {
		Assertions.assertEquals(Utils.makeShortPn("98A1234567890_CPD_P-11_D"),"98A12345678_D");
		
		Assertions.assertEquals(Utils.makeShortPn("98A1234567890_CPD-11_D"),"98A12345678_D");
		
		Assertions.assertEquals(Utils.makeShortPn("98A1234567890G01P01_NQ_D_-"),"98A12345678_D");
		
		Assertions.assertEquals(Utils.makeShortPn("98A1234567890G01P01NQ_D_-"),"98A12345678_D");
		
		Assertions.assertEquals(Utils.makeShortPn("9C12345G01P01NQ_D_-"),"9C12345_D");
		
		Assertions.assertEquals(Utils.makeShortPn("9C12345-67G01P01NQ_D_-"),"9C12345-67_D");
		
		Assertions.assertEquals(Utils.makeShortPn("9C12345G01P01NQ-11_D_-"),"9C12345_D");
		
		Assertions.assertEquals(Utils.makeShortPn("856A1234567890G01P01NQ_D"),"856A1234_D");
		
		Assertions.assertEquals(Utils.makeShortPn("RRT123456G01P01NQ_A_-"),"RRT123456_A");
		
		Assertions.assertEquals(Utils.makeShortPn("HU12345G01P01NQ_A_-"),"HU12345_A");
		
		Assertions.assertEquals(Utils.makeShortPn("330A12345678G01P01NQ_A_-"),"330A123456_A");
		
		Assertions.assertEquals(Utils.makeShortPn("9401M01G01P01NQ_A_-"),"9401M01_A");
		
		Assertions.assertEquals(Utils.makeShortPn("A12345-1_A_-"),"A12345_A");
		
		Assertions.assertEquals(Utils.makeShortPn("956A1001_QU_A"),"956A1001_A");
	}
	
	@Test
	public void testDateDiff() throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        
        Date start = fmt.parse("2010-01-02");
        Date end = fmt.parse("2010-03-02");
		long diff = Utils.dateDiff(start, end);
		log.debug("diff:"+diff);
		Assertions.assertEquals(diff, 59);
	}
	
	
}
