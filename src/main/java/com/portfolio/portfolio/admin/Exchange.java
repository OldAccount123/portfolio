package com.portfolio.portfolio.admin;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Exchange {
	@Id
	private ObjectId id;

	private String name;
	// example: https://kraken.com/api/price{pair1}{pair2}
	private String restPriceApiUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRestPriceApiUrl() {
		return restPriceApiUrl;
	}

	public void setRestPriceApiUrl(String restPriceApiUrl) {
		this.restPriceApiUrl = restPriceApiUrl;
	}

}
