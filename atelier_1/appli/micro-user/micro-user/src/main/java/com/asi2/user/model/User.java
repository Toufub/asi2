package com.asi2.user.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String surname;
	private String password;
	private double money;

	public User() {
	}

	public User(String name, String surname, String password, double money) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.money = money;
	}

	public int GetId() {
		return id;
	}

	public void SetId(int id) {
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

	public void SetMoney(double money) {
		this.money = money;
	}

    @Override
    public String toString() {
        return "User{" +
                "id =" + id +
                ", name ='" + name + '\'' +
                ", password ='" + password + '\'' +
                ", balance =" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return this.GetId() == other.GetId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
