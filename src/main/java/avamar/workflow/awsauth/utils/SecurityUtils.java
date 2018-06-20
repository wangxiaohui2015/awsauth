
package avamar.workflow.awsauth.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class is used to parse config/security.properties
 */
public class SecurityUtils {
	private static final Properties PROPERTIES = new Properties();
	private static final Logger LOGGER = LogUtil.getMainLogger(SecurityUtils.class);

	static {
		init();
	}

	private static void init() {
		PROPERTIES.clear();
		InputStream in = SecurityUtils.class.getResourceAsStream("/config/security.properties");
		try {
			PROPERTIES.load(in);
		} catch (IOException e) {
			LOGGER.error("Failed to load config.properties file.", e);
		}
	}

	/**
	 * Check user name and password.
	 * 
	 * @param userName
	 *            User name
	 * @param password
	 *            password
	 * @return true: user name and password is correct, false: user name and
	 *         password is in correct.
	 */
	public static synchronized boolean checkUser(String userName, String password) {
		// Initialize PROPERTIES, so no need to reboot server after change security.properties
		// This is not good when high concurrency.
		init();
		if (userName == null || password == null) {
			return false;
		}
		String psw = PROPERTIES.getProperty(userName);
		if (null != psw && password.equalsIgnoreCase(psw)) {
			return true;
		}
		return false;
	}
}
