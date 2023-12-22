package com.diazG.financialmovementsapp.dao;


import com.diazG.financialmovementsapp.dto.request.ExpenseRequestDto;
import com.diazG.financialmovementsapp.dto.response.ExpenseResponseDto;
import com.diazG.financialmovementsapp.entities.Expense;
import com.diazG.financialmovementsapp.exceptions.DAOException;

import java.util.List;

// van todas las operaciones del CRUD
public interface ExpenseRepository {
    Integer insert(Expense expense);
    List<Expense> getAll() throws DAOException;

    Expense getById(Long id) throws DAOException;
    Integer delete(Long id) throws DAOException;
    Integer update(Long id, Expense expense) throws DAOException;
}
