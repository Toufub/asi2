package com.asi2.common.model;

public class CardDTO {

	private int id;
	private String name;
	private String description;
	private String affinity;
	private String energy;
	private Double price;
	private String family;
	private int userId;
	private int transactionId;

	public CardDTO() {
	}

	public CardDTO(int id, String name,String description, String affinity, String energy, Double price, String family, int userId, int transactionId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.affinity = affinity;
		this.energy = energy;
		this.price = price;
		this.family = family;
		this.userId = userId;
		this.transactionId = transactionId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getAffinity() {
		return affinity;
	}

	public String getEnergy() {
		return energy;
	}

	public Double getPrice(){
		return price;
	}

	public String getFamily() {
		return family;
	}

	public int getUserId() {
		return userId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setId() {
		this.id = id;
	}

	public void setName() {
		this.name = name;
	}

	public void setDescription() {
		this.description = description;
	}

	public void setAffinity() {
		this.affinity = affinity;
	}

	public void setEnergy() {
		this.energy = energy;
	}

	public void setPrice(){
		this.price = price;
	}

	public void setFamily() {
		this.family = family;
	}

	public void setUserId() {
		this.userId = userId;
	}

	public void setTransactionId() {
		this.transactionId = transactionId;
	}


}

