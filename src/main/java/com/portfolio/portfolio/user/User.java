package com.portfolio.portfolio.user;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.portfolio.portfolio.admin.Coin;

@Document
public class User {
	@Id
	private ObjectId id;
	private String login;
	private String email;

	public User() {
		
	}
	
	public User(String login, String email) {
		super();
		this.login = login;
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
