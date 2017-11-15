package com.portfolio.portfolio.price;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Exchange {
	@Id
	private ObjectId id;

	private String name;
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
