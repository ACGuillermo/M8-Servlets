package com.prueba.com;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Config.java
 * Class implementation for retrieving the configuration from the properties file.
 * @author  Guillermo
 * @since   2020-04-30
 */
public class Config {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private String db = null;
	
	/**
	* 
	* Config
	* Class constructor
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
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
	
	/**
	* 
	* getDb
	* Method for getting the db url from the config.properties file.
	* @return db database absolute path.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public String getDb () {
		return db;
	}
}