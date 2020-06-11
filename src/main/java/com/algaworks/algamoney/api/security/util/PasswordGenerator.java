package com.algaworks.algamoney.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	
	// private static String password = "m0b1l30";	
	private static String password = "admin";	
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode(password));
	}
}
