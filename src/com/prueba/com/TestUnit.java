package com.prueba.com;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUnit {
   
   @Test
   public void testGetConnection() {
	   DataConnection dataConnection = new DataConnection();
	   assertNotNull(dataConnection.getConnection());
   }
   
   @Test
   public void closeConnection() throws SQLException {
	   DataConnection dataConnection = new DataConnection();
	   assertNotNull(dataConnection.getConnection());
	   dataConnection.closeConnection(dataConnection.getConnection());
	   assertTrue(dataConnection.getConnection().isClosed());
   }
   
   
   @Test
   public void emailRegex() {
	   Rgx rgx = new Rgx();
	   assertTrue(rgx.emailRegex("test@gmail.com"));
	   assertFalse(rgx.emailRegex("@emai.es"));
	   assertFalse(rgx.emailRegex("test.gmail.com"));
	   assertFalse(rgx.emailRegex("test@.es"));
	   assertFalse(rgx.emailRegex("@emai.es"));
	   assertFalse(rgx.emailRegex("asd@@gmail.com"));
	   assertTrue(rgx.emailRegex("1@1.com"));
	   assertTrue(rgx.emailRegex("as@1.com"));
	   assertTrue(rgx.emailRegex("1@as.com"));
   }
   
   @Test
   public void nombreRegex( ) {
	   Rgx rgx = new Rgx();
	   assertTrue(rgx.nombreRegex("t"));
	   assertTrue(rgx.nombreRegex("t2"));
	   assertTrue(rgx.nombreRegex("t33"));
	   assertTrue(rgx.nombreRegex("t444"));
	   assertTrue(rgx.nombreRegex("ASDzxa"));
	   assertTrue(rgx.nombreRegex("123456789"));
	   assertFalse(rgx.nombreRegex("t-123"));
	   assertFalse(rgx.nombreRegex("test;"));
	   assertFalse(rgx.nombreRegex("testº"));
	   assertFalse(rgx.nombreRegex(""));
	   assertFalse(rgx.nombreRegex("12345678910"));
   }
   
   @Test public void passwordRegex( ) {
	   Rgx rgx = new Rgx();
	   
   }
}

