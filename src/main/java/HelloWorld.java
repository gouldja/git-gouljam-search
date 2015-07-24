import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gouldja.common.framework.ApplicationConfiguration;

public class HelloWorld {
	
	private static final Logger logger = LogManager.getLogger(HelloWorld.class);

	public static void main(String[] args) {
		logger.debug("Running Message");
		
		logger.debug(ApplicationConfiguration.getApplicationProperty("app.name"));

		
	}

}
