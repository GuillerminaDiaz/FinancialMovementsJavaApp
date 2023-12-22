package com.diazG.financialmovementsapp.dto.request;

public class ExpenseRequestDto {
    private Double amount;
    private ExpenseCategoryRequestDto categoryRequestDto;
    private String date;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseCategoryRequestDto getCategoryRequestDto() {
        return categoryRequestDto;
    }

    public void setCategoryRequestDto(ExpenseCategoryRequestDto categoryRequestDto) {
        this.categoryRequestDto = categoryRequestDto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
