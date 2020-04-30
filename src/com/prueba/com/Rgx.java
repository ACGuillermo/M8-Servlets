package com.prueba.com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* 
* Rgx.java
* Class implementation for checking different regex matching
* @author  Guillermo
* @since   2020-04-30
*  
*/
public class Rgx {
	String regexEmail;
	String regexNombre;
	String regexPassword;
	
	Pattern patternEmail; 
	Pattern patternNombre;
	Pattern patternPassword;
	
	public Rgx() {
		regexEmail = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
		regexNombre = "^[a-zA-Z0-9]{1,10}$";
		regexPassword = "^.{8,}$";
		
		patternEmail = Pattern.compile(regexEmail);
		patternNombre = Pattern.compile(regexNombre);
		patternPassword = Pattern.compile(regexPassword);
	}
	public boolean emailRegex(String email) {
		Matcher matcherEmail = patternEmail.matcher(email);
		return matcherEmail.matches();
	}
	
	public boolean nombreRegex(String nombre) {
		Matcher matcherNombre = patternNombre.matcher(nombre);
		return matcherNombre.matches();
	}
	
	public boolean passwordRegex(String password) {
		Matcher matcherPassword = patternPassword.matcher(password);
		return matcherPassword.matches();
	}
}
