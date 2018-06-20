
package avamar.workflow.awsauth.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.warrenstrange.googleauth.GoogleAuthenticator;

import avamar.workflow.awsauth.module.SecretCode;

/**
 * This class is used to parse config/keys.properties
 */
public class KeysUtils {
	private static final Properties PROPERTIES = new Properties();
	private static final Logger LOGGER = LogUtil.getMainLogger(KeysUtils.class);

	static {
		init();
	}

	private static void init() {
		PROPERTIES.clear();
		InputStream in = KeysUtils.class.getResourceAsStream("/config/keys.properties");
		try {
			PROPERTIES.load(in);
		} catch (IOException e) {
			LOGGER.error("Failed to load keys.properties file.", e);
		}
	}

	/**
	 * Get all secret code list
	 * 
	 * @return Secret code list
	 */
	public static List<SecretCode> getAllSecretCode() {
		List<SecretCode> results = new ArrayList<SecretCode>();
		Set<Object> keys = PROPERTIES.keySet();
		Iterator<Object> it = keys.iterator();
		while (it.hasNext()) {
			SecretCode secretCode = new SecretCode();
			// User
			String user = (String) it.next();
			secretCode.setUserName(user);
			// Key
			GoogleAuthenticator gAuth = new GoogleAuthenticator();
			String key = PROPERTIES.getProperty(user);
			secretCode.setSecretKey(key);
			// Secret code
			int code = gAuth.getTotpPassword(key);
			secretCode.setSecretCode(code);
			Calendar calendar = Calendar.getInstance();
			// Second
			int second = calendar.get(Calendar.SECOND);
			secretCode.setSecond(second);
			results.add(secretCode);
		}
		return results;
	}
}
