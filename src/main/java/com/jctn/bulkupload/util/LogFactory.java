/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.util;

import java.io.File;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Used for logging throughout the app.
 * @author martin
 */
public class LogFactory {

	private static Logger log4jLogger;
	private static final String DEFAULT_LOG_FILE = "jctn.log";
	private static final String DEFAULT_PATTERN = "%-4r [%t] %-5p %c %x - %m%n";
	private static Appender appender;

	static {
		try {
			appender = new FileAppender(
					new PatternLayout(DEFAULT_PATTERN),
					System.getProperty("user.home") + File.separator + DEFAULT_LOG_FILE,
					true);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Returns the configured logger for this app.
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class clazz) {
		log4jLogger = Logger.getLogger(clazz);
		log4jLogger.addAppender(appender);

		return log4jLogger;
	}
}
