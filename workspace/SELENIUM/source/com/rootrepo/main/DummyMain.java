package com.rootrepo.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyMain {

	static Logger logger = LoggerFactory.getLogger(DummyMain.class);
	public static void main(String[] args) {
		//BasicConfigurator.configure();
		
		// Load properties
		try {
			logger.trace("Inside DummyMain");
			Class.forName("com.rootrepo.main.ClassLoader");
			System.out.println(ClassLoader.getGlobalInitProperties());
		} catch (ClassNotFoundException e) {
			System.out.println("Error ClassLoader not found");
			e.printStackTrace();
		}
		System.out.println();
	}
}
