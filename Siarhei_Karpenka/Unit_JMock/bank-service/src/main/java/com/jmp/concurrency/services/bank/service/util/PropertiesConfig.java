package com.jmp.concurrency.services.bank.service.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesConfig {
	
	private static final Logger logger = Logger.getLogger(PropertiesConfig.class);
	
	private static final Properties properties = new Properties();
	
	static {
		try {
			properties.load(PropertiesConfig.class.getResourceAsStream(AppProperties.CONFIG_FILE_PATH));
		} catch (IOException e) {
			logger.error("Unnable to load properties file", e);
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
