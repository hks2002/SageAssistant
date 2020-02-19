package sageassistant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UtilsTests {

	@Test
	public void testReadFileContent() {
		String sql = Utils.readFileContent("sql/test.sql");
		assertEquals(sql, "This is test sql");
	}
	
	
}
