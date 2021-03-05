package sageassistant;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sageassistant.dao.PnMapper;
import sageassistant.model.PnRootPn;

@SpringBootTest
public class DBTests {
	static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private PnMapper pnMapper;
	
	@Test
	 public void speedTest() {
		long start = System.currentTimeMillis();
		logger.info("Start ...");
		for (int i=0; i<10; i++) {
			List<PnRootPn> listPage = pnMapper.findPnByLike("956A10%");
			logger.info(listPage.toString());
		}
		long end = System.currentTimeMillis();
		 logger.info("End! time:{}", end-start);
	 }
}
