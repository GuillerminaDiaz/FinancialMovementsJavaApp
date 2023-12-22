package com.diazG.financialmovementsapp.impl;

import com.diazG.financialmovementsapp.dao.ExpenseRepository;
import com.diazG.financialmovementsapp.dao.impl.ExpenseRepositoryImplH2;
import com.diazG.financialmovementsapp.entities.Expense;
import com.diazG.financialmovementsapp.entities.ExpenseCategory;
import com.diazG.financialmovementsapp.exceptions.DAOException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExpenseRepositoryImplH2Test {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ExpenseRepositoryImplH2 expenseRepository;


    @Test
    void testGetAllExpenses() throws DAOException {

        Mockito.when(jdbcTemplate.query(any(String.class), any(ExpenseRepositoryImplH2.ExpenseRowMapper.class)))
                .thenReturn(Arrays.asList(new Expense(), new Expense()));


        List<Expense> expenses = expenseRepository.getAll();


        assertEquals(2, expenses.size());
    }

    @Test
    void testGetExpenseById() throws DAOException {

        Mockito.when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(int[].class), any(ExpenseRepositoryImplH2.ExpenseRowMapper.class)))
                .thenReturn(new Expense());


        Expense result = expenseRepository.getById(1L);


        assertEquals(Expense.class, result.getClass());


        Mockito.verify(jdbcTemplate).queryForObject(eq("SELECT * FROM Expense WHERE id = ?"), any(Object[].class), any(int[].class), any(ExpenseRepositoryImplH2.ExpenseRowMapper.class));
    }

    @Test
    void testDeleteExpense() throws DAOException {

        Mockito.when(jdbcTemplate.update(any(String.class), any(Object[].class)))
                .thenReturn(1);


        Integer result = expenseRepository.delete(1L);


        assertEquals(1, result);


        Mockito.verify(jdbcTemplate).update(eq("DELETE FROM Expense WHERE id = ?"), eq(1L));
    }

    @Test
    void testUpdateExpense() throws DAOException {

        Mockito.when(jdbcTemplate.update(any(String.class), any(Object[].class)))
                .thenReturn(1);


        Integer result = expenseRepository.update(1L, new Expense());


        assertEquals(1, result);


        Mockito.verify(jdbcTemplate).update(
                eq("UPDATE Expense SET amount = ?, category_name = ? WHERE id = ?"),
                any(),
                any(),
                eq(1L)
        );
    }

}
