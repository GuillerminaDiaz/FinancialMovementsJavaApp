package com.diazG.financialmovementsapp.interfaces;


import com.diazG.financialmovementsapp.exceptions.InvalidExpenseException;

@FunctionalInterface
public interface ExpenseAmountValidator {
    boolean validateAmount(double amount) throws InvalidExpenseException;
}
