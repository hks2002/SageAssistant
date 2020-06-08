package sageassistant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sageassistant.utils.Utils;

public class UtilsTests {
	private static final Logger log = LoggerFactory.getLogger(UtilsTests.class);
	
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
	public void testFindFiles() {
		String fpath = Thread.currentThread().getContextClassLoader().getResource("sql").getPath();
		Assertions.assertEquals(Utils.findFiles(fpath + "/XXXX").length,0);
		Assertions.assertEquals(Utils.findFiles(fpath + "/test.sql").length,0);
		Assertions.assertTrue(Utils.findFiles(fpath).length==1);
		Assertions.assertTrue(Utils.findFiles(fpath)[0].equals("test.sql"));
	}
	
	
	@Test
	public void testMakeShortPn() {
		log.debug(Utils.makeShortPn("98A1234567890G01P01NQ_D_-"));
		Assertions.assertEquals(Utils.makeShortPn("98A1234567890G01P01NQ_D"),"98A12345678NQ_D");
		
		log.debug(Utils.makeShortPn("9C12345G01P01NQ_D_-"));
		Assertions.assertEquals(Utils.makeShortPn("9C12345G01P01NQ_D_-"),"9C12345NQ_D");
		
		log.debug(Utils.makeShortPn("856A1234567890G01P01NQ_D_-"));
		Assertions.assertEquals(Utils.makeShortPn("856A1234567890G01P01NQ_D"),"856A1234NQ_D");
		
		log.debug(Utils.makeShortPn("RRT123456G01P01NQ-1_A_-"));
		Assertions.assertEquals(Utils.makeShortPn("RRT123456G01P01NQ-1_A_-"),"RRT123456NQ-1_A");
		
		log.debug(Utils.makeShortPn("HU12345G01P01NQ-1_A_-"));
		Assertions.assertEquals(Utils.makeShortPn("HU12345G01P01NQ-1_A_-"),"HU12345NQ-1_A");
		
		log.debug(Utils.makeShortPn("330A12345678G01P01NQ_A_-"));
		Assertions.assertEquals(Utils.makeShortPn("330A12345678G01P01NQ_A_-"),"330A123456NQ_A");
		
		log.debug(Utils.makeShortPn("9401M01G01P01NQ_A_-"));
		Assertions.assertEquals(Utils.makeShortPn("9401M01G01P01NQ_A_-"),"9401M01NQ_A");
		
		log.debug(Utils.makeShortPn("A12345-1_A_-"));
		Assertions.assertEquals(Utils.makeShortPn("A12345-1_A_-"),"A12345_A");
	}
		
}
