package com.portfolio.portfolio.user;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.portfolio.portfolio.admin.Coin;
import com.portfolio.portfolio.price.Portfolio;

@Document
public class User {
	@Id
	private ObjectId id;
	private String login;
	private String password;
	private String email;
	private Portfolio portfolio;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

}
