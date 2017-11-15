package com.portfolio.portfolio.price;

import java.util.HashMap;
import java.util.Map;

import com.portfolio.portfolio.admin.Coin;

public class Portfolio {
	private float valueInFiat;
	private HashMap<String, Coin> portfolioMap;

	public float getValueInFiat() {
		return valueInFiat;
	}

	public void setValueInFiat(float valueInFiat) {
		this.valueInFiat = valueInFiat;
	}

	public HashMap<String, Coin> getPortfolioMap() {
		return portfolioMap;
	}

	public void setPortfolioMap(HashMap<String, Coin> portfolioMap) {
		this.portfolioMap = portfolioMap;
	}

}
