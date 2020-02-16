package sageassistant;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class UtilsTests {

	@Test
	@Order(1)
	public void testReadFileContent() {
		String sql = Utils.readFileContent("sql/test.sql");
		log.debug(sql);
		Assert.assertEquals(sql, "This is test sql");
	}
	
	
}
