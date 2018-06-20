package avamar.workflow.awsauth.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * LogUtil, used to record logs.
 * 
 * @author sunny
 */
public class LogUtil {

	// Root logger.
	private static final Logger rootLogger = Logger.getRootLogger();

	// Log file root file
	private static String logFileRootFolder = "";

	// Agent register log file name
	private static final String MAIN_LOG = "main.log";

	// Default log level
	private static Level defaultLogLevel = Level.WARN;

	static {
		initLogParams();
	}

	/**
	 * Get main logger.
	 * 
	 * @param clazz
	 *            Class object
	 * @return Main logger
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getMainLogger(Class clazz) {
		return getLogger(clazz, MAIN_LOG);
	}

	/*
	 * Initialize log parameters.
	 */
	private static void initLogParams() {
		RollingFileAppender appender = (RollingFileAppender) rootLogger
				.getAppender("main");

		// Initialize log file root path
		if (null != appender) {
			String logFilePath = appender.getFile();
			logFileRootFolder = logFilePath.substring(0,
					logFilePath.lastIndexOf("/"))
					+ File.separator;
		} else {
			logFileRootFolder = "target/logs/";
		}

		// Initialize default log level
		defaultLogLevel = rootLogger.getLevel();
	}

	/*
	 * Get logger.
	 * 
	 * @param clazz Class object
	 * 
	 * @param logFileName log file name
	 * 
	 * @return Logger
	 */
	@SuppressWarnings("rawtypes")
	private static Logger getLogger(Class clazz, String logFileName) {
		return getLogger(clazz, logFileName, defaultLogLevel);
	}

	/*
	 * Get logger.
	 * 
	 * @param clazz Class object
	 * 
	 * @param logFileName log file name
	 * 
	 * @param level Log level
	 * 
	 * @return Logger
	 */
	@SuppressWarnings("rawtypes")
	private static Logger getLogger(Class clazz, String logFileName, Level level) {
		Logger logger = Logger.getLogger(clazz);
		logger.removeAllAppenders();
		logger.setAdditivity(false);
		logger.setLevel(level);
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("%-d{yyyy-MM-dd HH:mm:ss SSS} [%c]-[%p] - %m%n");
		try {
			RollingFileAppender appender = new RollingFileAppender(layout,
					logFileRootFolder + logFileName);
			appender.setMaxFileSize("20MB");
			appender.setMaxBackupIndex(10);
			logger.addAppender(appender);
		} catch (IOException e) {
			rootLogger.error("IOException occurred while get logger.", e);
		}
		return logger;
	}
}
