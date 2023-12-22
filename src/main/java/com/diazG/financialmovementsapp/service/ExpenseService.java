package com.diazG.financialmovementsapp.service;

import com.diazG.financialmovementsapp.dto.request.ExpenseRequestDto;
import com.diazG.financialmovementsapp.dto.response.ExpenseResponseDto;
import com.diazG.financialmovementsapp.exceptions.DAOException;

import java.util.List;

public interface ExpenseService {
    String createExpense(ExpenseRequestDto expenseRequestDto);
    List<ExpenseResponseDto> selectAllExpensesDto() throws DAOException;
    ExpenseResponseDto selectExpenseById(Long id) throws DAOException;
    String expenseDeleted(Long id) throws DAOException;

    String expenseUpdated(Long id, ExpenseRequestDto expenseRequestDto) throws DAOException;


}
