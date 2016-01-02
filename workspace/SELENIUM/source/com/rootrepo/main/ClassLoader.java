package com.rootrepo.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassLoader {

	static Logger logger = LoggerFactory.getLogger(ClassLoader.class);
	private static Properties globalInitProperties;
	static {
		logger.trace("inside static of Class loader class");
		setGlobalInitProperties();
	}

	private static void setGlobalInitProperties() {

		Properties initProperties = null;
		FileInputStream initFileInputStream = null;
		try {
			initProperties = new Properties();
			initFileInputStream = new FileInputStream(
					"D:\\selenium\\workspace\\SELENIUM\\config\\InitLoad.properties");
			initProperties.load(initFileInputStream);

		} catch (FileNotFoundException e) {
			System.out.println("Error InitLoad.properties file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out
					.println("Error while reading properties from file - InitLoad.properties");

			e.printStackTrace();
		} finally {
			if (initFileInputStream != null) {
				try {
					initFileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		globalInitProperties = initProperties;
	}

	public static Properties getGlobalInitProperties() {
		return globalInitProperties;
	}
}