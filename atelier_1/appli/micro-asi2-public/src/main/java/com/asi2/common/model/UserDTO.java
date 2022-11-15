package com.asi2.common.model;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -6470090944414208495L;
    private Integer id;
    private String login;
    private String lastName;
    private String surName;
    private String pwd;
    private double account;

    public UserDTO() {

    }

    public UserDTO(Integer id, String login, String lastName, String surName, String pwd, double account) {
        this.id = id;
        this.login = login;
        this.lastName = lastName;
        this.surName = surName;
        this.pwd = pwd;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }
}
