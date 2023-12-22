package com.diazG.financialmovementsapp.interfaces;



import com.diazG.financialmovementsapp.entities.Expense;

import java.util.List;

public interface ExpenseCalculator {
    double calculateExpense(Expense expense);
    double calculateTotalExpense (List<Expense> expenses);
}
