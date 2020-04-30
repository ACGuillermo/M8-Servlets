package com.prueba.com;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	private String db = null;
	
	public Config() {
		Properties prop = new Properties();
		InputStream is = null;
		
		try {
			is = new FileInputStream("C:\\Users\\Guillermo\\Desktop\\WorkStation\\M8-Servlets\\resources\\config.properties");
			prop.load(is);
		} catch(IOException e) {
			LOGGER.log(Level.SEVERE, e.toString());
		}
		
		db = prop.getProperty("DB");
	}
	
	public String getDb () {
		return db;
	}
}