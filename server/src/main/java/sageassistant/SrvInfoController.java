package sageassistant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SrvInfoController {
	 @Value("${project.name}")
	 private String name;

	 @Value("${project.version}")
	 private String version;
	
	 @Value("${spring.boot.version}")
	 private String springBootVersion;
	 
	@GetMapping("/Data/SrvVersion")
	public String getVersion() {
	 return version;
	}

	@GetMapping("/Data/SrvName")
	public String getName() {
		return name;
	}

	@GetMapping("/Data/SrvInfo")
	public String getInfo() {
		return "{\"name\":\"" + name + "\",\"version\":\"" + version+ "\",\"springBootVersion\":\"" + springBootVersion + "\"}";
	}
}
