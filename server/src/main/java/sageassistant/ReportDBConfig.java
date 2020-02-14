package sageassistant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ReportDBConfig {
	private static final Logger logger = LoggerFactory.getLogger(ReportDBConfig.class);
	@Autowired
	private Environment env;
		
	ReportDBConfig() {
		logger.info("ReportDB Config...");
	}

	public String getDriveClassName() {
		return env.getProperty("spring.datasource.driver-class-name");
	}

	public String getPassword() {
		return env.getProperty("spring.datasource.password");
	}
	@GetMapping("url")
	public String getUrl() {
		return env.getProperty("spring.datasource.url");
	}

	public String getUsername() {
		return env.getProperty("spring.datasource.username");
	}

}
