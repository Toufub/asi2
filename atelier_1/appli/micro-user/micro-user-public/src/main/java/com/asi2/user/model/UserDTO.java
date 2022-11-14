package com.asi2.user.model;

public class UserDTO {

    private String id;
    private String name;
    private String surname;
    private String password;
    private double money;

    public UserDTO() {
    }

    public UserDTO(String id, String name, String surname, String password, double money) {
        this.id = id;
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
