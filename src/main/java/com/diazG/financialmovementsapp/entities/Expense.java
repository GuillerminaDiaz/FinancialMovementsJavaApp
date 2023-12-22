package com.diazG.financialmovementsapp.entities;

public class Expense {
    private Long id;
    private Double amount;
    private Long categoryId;
    private String categoryName;

    private String date;

    public Expense() {
    }

    public Expense(Long categoryId, String categoryName, Double amount, String date) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.amount = amount;
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
