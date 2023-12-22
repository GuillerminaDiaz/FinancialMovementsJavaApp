package com.diazG.financialmovementsapp.dao;


import com.diazG.financialmovementsapp.dao.dtoExp.ExpenseCategoryDto;
import com.diazG.financialmovementsapp.entities.ExpenseCategory;
import com.diazG.financialmovementsapp.exceptions.DAOException;

public interface ExpenseCategoryDao {
    void insert(ExpenseCategoryDto expense) throws DAOException;
    ExpenseCategory getCategoryByName(String name) throws DAOException;
}
