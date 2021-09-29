package com.example.demo.entity;

public class CurrentPrice {
	
	Double value;
	String currencyCode;
	
	
	public CurrentPrice(Double value, String currencyCode) {
		super();
		this.value = value;
		this.currencyCode = currencyCode;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
}
