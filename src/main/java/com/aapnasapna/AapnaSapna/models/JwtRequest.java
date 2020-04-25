package com.aapnasapna.AapnaSapna.models;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String userName;
	private String password;

	// need default constructor for JSON Parsing
	public JwtRequest() {

	}

	public JwtRequest(String username, String password) {
		this.setUserName(username);
		this.setPassword(password);
	}

	public String getPassword() {
		return this.password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String username) {
		this.userName = username;
	}
}