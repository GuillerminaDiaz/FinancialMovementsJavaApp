package com.diazG.financialmovementsapp.dto.response;

public class ExpenseResponseDto {
    private double amount;
    private ExpenseCategoryResponseDto expenseCategoryResponseDto;
    private String date;

    public ExpenseResponseDto() {
    }

    public ExpenseResponseDto(double amount, ExpenseCategoryResponseDto expenseCategoryResponseDto, String date) {
        this.amount = amount;
        this.expenseCategoryResponseDto = expenseCategoryResponseDto;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseCategoryResponseDto getExpenseCategoryResponseDto() {
        return expenseCategoryResponseDto;
    }

    public void setExpenseCategoryResponseDto(ExpenseCategoryResponseDto expenseCategoryResponseDto) {
        this.expenseCategoryResponseDto = expenseCategoryResponseDto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
