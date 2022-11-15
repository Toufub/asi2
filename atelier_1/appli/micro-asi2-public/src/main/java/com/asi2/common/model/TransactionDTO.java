package com.asi2.common.model;

import java.io.Serializable;
import java.util.Date;

public class TransactionDTO implements Serializable {
	private Integer id;
    private double  price;
    private Integer  card_id;
    private Integer  buyerId;
    private Integer  user_id;

    private Date transactionDate;

    public TransactionDTO() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCardId() {
        return card_id;
    }

    public void setCardId(Integer card_id) {
        this.card_id = card_id;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
