package com.asi2.user.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String name;
	private String surname;
	private String password;
	private double money;

	public User() {
		UUID UUID_1 = UUID.randomUUID();
		this.id = UUID_1.toString();
	}

	public User(String name, String surname, String password, double money) {
		UUID UUID_1 = UUID.randomUUID();
		this.id = UUID_1.toString();
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.money = money;
	}

	public String GetId() {
		return id;
	}

	public void SetId(String id) {
		this.id = id;
	}

	public String GetName() {
		return name;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public String GetSurname() {
		return surname;
	}

	public void SetSurname(String surname) {
		this.surname = surname;
	}

	public String GetPassword() {
		return password;
	}

	public void SetPassword(String password) {
		this.password = password;
	}

	public double GetMoney() {
		return this.money;
	}

	public void SetMonet(double money) {
		this.money = money;
	}
}
