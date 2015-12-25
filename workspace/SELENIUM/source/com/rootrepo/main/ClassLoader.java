package com.rootrepo.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ClassLoader {

	public static void main(String[] args) {
		
		Properties initProperties = null;
		FileInputStream initFileInputStream = null;
		try {
			initProperties = new Properties();
			initFileInputStream = new FileInputStream("D:\\selenium\\workspace\\SELENIUM\\source\\com\\rootrepo\\main\\InitLoad.properties");
			initProperties.load(initFileInputStream);
			
		} catch (FileNotFoundException e) {
			System.out.println("Error InitLoad.properties file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while reading properties from file - InitLoad.properties");
			if (initFileInputStream != null) {
				
			}
			e.printStackTrace();
		}
	}
}
