package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CapstoneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneProjectApplication.class, args);
	}

}

//@SpringBootApplication
//public class CapstoneProjectApplication {
//
//	private static final Logger logger = LoggerFactory.getLogger(CapstoneProjectApplication.class);
//
//	public static void main(String[] args) {
//		SpringApplication.run(CapstoneProjectApplication.class, args);
//
//		String processName = ManagementFactory.getRuntimeMXBean().getName();
//		long pid = Long.parseLong(processName.split("@")[0]);
//		String javaVersion = System.getProperty("java.version");
//
//		logger.info("Starting CapstoneProjectApplication using Java {} with PID {}", javaVersion, pid);
//		// Altri messaggi di log...
//	}
//}
