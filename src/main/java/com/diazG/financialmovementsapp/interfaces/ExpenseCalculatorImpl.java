package com.diazG.financialmovementsapp.interfaces;



import com.diazG.financialmovementsapp.entities.Expense;

import java.util.List;

public class ExpenseCalculatorImpl implements ExpenseCalculator {
    @Override
    public double calculateExpense(Expense expense) {
        return expense.getAmount();
    }

    @Override
    public double calculateTotalExpense(List<Expense> expenses) {
        double total=0;
        for (Expense item: expenses){
            total += item.getAmount();
        }
        return total;
    }
}
