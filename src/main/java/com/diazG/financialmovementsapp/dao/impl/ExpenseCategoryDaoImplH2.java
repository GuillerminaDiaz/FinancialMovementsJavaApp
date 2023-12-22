package com.diazG.financialmovementsapp.dao.impl;


import com.diazG.financialmovementsapp.dao.ExpenseCategoryDao;
import com.diazG.financialmovementsapp.dao.dtoExp.ExpenseCategoryDto;
import com.diazG.financialmovementsapp.entities.ExpenseCategory;
import com.diazG.financialmovementsapp.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseCategoryDaoImplH2 implements ExpenseCategoryDao {
    private static final  String GET_CATEGORY_BY_NAME= "SELECT * FROM expense_app.expensecategory WHERE name = ?";
    private static final  String INSERT_INTO_EXPENSE_CATEGORY= "INSERT INTO expense_app.expensecategory (name) VALUES (?)";

    private final Connection connection;
    public ExpenseCategoryDaoImplH2(Connection connection){
        this.connection=connection;
    }
    @Override
    public void insert(ExpenseCategoryDto expenseCategoryDto) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_EXPENSE_CATEGORY)) {

            ExpenseCategory expenseCategory = mapDtoToExpenseCategory(expenseCategoryDto);

            statement.setString(1, expenseCategory.getName());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Error al insertar el gasto, ninguna fila fue afectada.");
            }
        } catch (DAOException | SQLException e) {
            assert e instanceof SQLException;
            throw new DAOException("Error al insertar el gasto", (SQLException) e);
        }

    }

    @Override
    public ExpenseCategory getCategoryByName(String name) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ExpenseCategory(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el gasto por ID", e);
        }
    }
    private ExpenseCategory mapDtoToExpenseCategory(ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(expenseCategoryDto.getName());
        return expenseCategory;
    }
}
