package com.example.demo.entity;

public class Target {
	
	Long id;
	String name;
	CurrentPrice currentPrice;
	
	
	public Target(Long id, String name, CurrentPrice currentPrice) {
		super();
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	 public CurrentPrice getCurrentPrice() { return currentPrice; } public void
	 setCurrentPrice(CurrentPrice currentPrice) { this.currentPrice =
	 currentPrice; }
	 
	
}
