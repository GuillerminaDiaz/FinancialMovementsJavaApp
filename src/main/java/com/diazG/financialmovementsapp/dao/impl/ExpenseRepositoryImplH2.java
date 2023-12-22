package com.diazG.financialmovementsapp.dao.impl;

import com.diazG.financialmovementsapp.dao.ExpenseRepository;
import com.diazG.financialmovementsapp.dto.request.ExpenseRequestDto;
import com.diazG.financialmovementsapp.dto.response.ExpenseResponseDto;
import com.diazG.financialmovementsapp.entities.Expense;
import com.diazG.financialmovementsapp.entities.ExpenseCategory;
import com.diazG.financialmovementsapp.exceptions.DAOException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExpenseRepositoryImplH2 implements ExpenseRepository {

    private static final String INSERT_INTO_EXPENSE= "INSERT INTO Expense (amount, category_id, category_name, date) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_EXPENSES= "SELECT * FROM Expense";
    private static final String UPDATE ="UPDATE Expense SET amount = ?, category_name = ? WHERE id = ?";
    private static final String DELETE= "DELETE FROM Expense WHERE id = ?";
    private static final String GET_EXPENSE_BY_ID= "SELECT * FROM Expense WHERE id = ?";
    private static final String INSERT_INTO_CATEGORY_EXPENSE= "INSERT INTO ExpenseCategory (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM ExpenseCategory WHERE name = ?)";

          //  "INSERT INTO ExpenseCategory (name) VALUES (?)";
    private static final String SELECT_FROM_EXPENSE_CATEGORY_BY_NAME= "SELECT * FROM ExpenseCategory WHERE name = ? LIMIT 1";
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Integer insert(Expense expense) {

        String categoryName = expense.getCategoryName() != null ? expense.getCategoryName().toLowerCase() : null;

        jdbcTemplate.update(INSERT_INTO_CATEGORY_EXPENSE, categoryName, categoryName);

        Object[] params = {expense.getCategoryName()};
        int[] types= {1};

        ExpenseCategory expenseCategory = jdbcTemplate.queryForObject(SELECT_FROM_EXPENSE_CATEGORY_BY_NAME, params, types, new ExpenseCategoryRowMapper());


            return jdbcTemplate.update(INSERT_INTO_EXPENSE, expense.getAmount(), expenseCategory.getId(), expenseCategory.getName(), expense.getDate());

    }

    public static class ExpenseCategoryRowMapper implements RowMapper<ExpenseCategory>{

        @Override
        public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExpenseCategory expenseCategory= new ExpenseCategory();
            expenseCategory.setId(rs.getLong("id"));
            expenseCategory.setName(rs.getString("name"));
            return expenseCategory;
        }
    }

    @Override
    public List<Expense> getAll() throws DAOException {

        return jdbcTemplate.query(GET_ALL_EXPENSES, new ExpenseRowMapper());
    }

    public static class ExpenseRowMapper implements RowMapper<Expense>{

        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense= new Expense();
            expense.setId(rs.getLong("id"));
            expense.setAmount(rs.getDouble("Amount"));
            expense.setCategoryId(rs.getLong("category_id"));
            expense.setCategoryName(rs.getString("category_name"));
            expense.setDate(rs.getString("date"));
            return expense;
        }
    }

    @Override
    public Expense getById(Long id) throws DAOException {
        Object[] params = {id};
        int[] types = {1};
        return jdbcTemplate.queryForObject(
                GET_EXPENSE_BY_ID,
                params, types,
                new ExpenseRowMapper());

    }

    @Override
    public Integer delete(Long id) throws DAOException {
        return jdbcTemplate.update(DELETE, id);

    }

    @Override
    public Integer update(Long id, Expense expense) throws DAOException {
        return jdbcTemplate.update(UPDATE, expense.getAmount(), expense.getCategoryName(), id);
    }

}
