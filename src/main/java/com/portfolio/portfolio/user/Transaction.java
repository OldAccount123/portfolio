package com.portfolio.portfolio.user;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {
	private Date date;
	private float price;
	
}
