package sageassistant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SrvApplicationTests {
	static final Logger logger = LogManager.getLogger();

	@Test
	void contextLoads() {
	}

	@Test
	public void log4j2Test() {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
}
