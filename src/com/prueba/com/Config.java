package com.prueba.com;

import java.io.*;
import java.util.*;

public class Config {
	
	public static String db = null;
	
	public Config() {
		Properties prop = new Properties();
		InputStream is = null;
		
		try {
			is = new FileInputStream("C:\\Users\\Guillermo\\Desktop\\WorkStation\\M8-Servlets\\resources\\config.properties");
			prop.load(is);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
		
		db = prop.getProperty("DB");
	}
	
	public String getDb () {
		return db;
	}
}