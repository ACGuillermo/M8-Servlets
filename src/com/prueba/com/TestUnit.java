package com.prueba.com;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

public class TestUnit {
   
   @Test
   public void testGetConnection() {
	   DataConnection dataConnection = new DataConnection();
	   assertNotNull(dataConnection.getConnection());
	   dataConnection.closeConnection(dataConnection.getConnection());
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
   
   @Test
   public void passwordRegex( ) {
	   Rgx rgx = new Rgx();
	   assertTrue(rgx.passwordRegex("12345678"));
	   assertTrue(rgx.passwordRegex("abcdefgh"));
	   assertTrue(rgx.passwordRegex("zº854'=¡"));
	   assertTrue(rgx.passwordRegex("_'^AZlsj22"));
	   assertFalse(rgx.passwordRegex("1234567"));
	   assertFalse(rgx.passwordRegex("abcdefg"));
   }
   
   @Test
   public void checkUser( ) throws SQLException {
	   DataConnection dataConnection = new DataConnection();
	   assertFalse(dataConnection.checkUser("TestFalso"));
	   assertFalse(dataConnection.checkUser("12348azaq"));
	   assertTrue(dataConnection.checkUser("Guille"));
	   assertTrue(dataConnection.checkUser("guille"));
	   assertTrue(dataConnection.checkUser("hola"));
	   
	   dataConnection.closeConnection(dataConnection.getConnection());
   }
   
   @Test
   public void insertUser( ) throws SQLException {
	   String test = "TestUser";
	   DataConnection dataConnection = new DataConnection();
	   dataConnection.dropUser(test);
	   assertFalse(dataConnection.checkUser(test));
	   dataConnection.insertUser(test, "passTest", "eTest");
	   assertTrue(dataConnection.checkUser(test));
	   dataConnection.dropUser(test);
	   assertFalse(dataConnection.checkUser(test));
	   
	   dataConnection.closeConnection(dataConnection.getConnection());
   }
   
   @Test
   public void dropUser ( ) throws SQLException {
	   String test = "TestUser";
	   DataConnection dataConnection = new DataConnection();
	   dataConnection.insertUser(test, "passTest", "eTest");
	   assertTrue(dataConnection.checkUser(test));
	   dataConnection.dropUser(test);
	   assertFalse(dataConnection.checkUser(test));
	   
	   dataConnection.closeConnection(dataConnection.getConnection());
	   
   }
}

