package com.diazG.financialmovementsapp.service.impl;

import com.diazG.financialmovementsapp.dao.ExpenseRepository;
import com.diazG.financialmovementsapp.dto.request.ExpenseRequestDto;
import com.diazG.financialmovementsapp.dto.response.ExpenseCategoryResponseDto;
import com.diazG.financialmovementsapp.dto.response.ExpenseResponseDto;
import com.diazG.financialmovementsapp.entities.Expense;
import com.diazG.financialmovementsapp.exceptions.DAOException;
import com.diazG.financialmovementsapp.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public String createExpense(ExpenseRequestDto expenseRequestDto) {
        String response= "Se registró el gasto";

        Expense expense = mapDtoToExpense(expenseRequestDto);

        Integer responseInserted= expenseRepository.insert(expense);
        if(responseInserted.equals(0)){
            System.out.println("No se insertó ningún registro");
        }
        return response;
    }

    @Override
    public List<ExpenseResponseDto> selectAllExpensesDto() throws DAOException {
        List<Expense> expenseList= expenseRepository.getAll();

        return expenseList.stream()
                .map(expense -> ExpenseToExpenseResponseDto(expense))
                .collect(Collectors.toList());

    }

    @Override
    public ExpenseResponseDto selectExpenseById(Long id) throws DAOException {
        Expense expense = expenseRepository.getById(id);
        ExpenseResponseDto expenseResponseDto= ExpenseToExpenseResponseDto(expense);
        return expenseResponseDto;
    }

    @Override
    public String expenseDeleted(Long id) throws DAOException {
        String response= "Registro eliminado";
        Integer responseDeleted= expenseRepository.delete(id);
        if(responseDeleted.equals(0)){
            System.out.println("No se eliminó ningún registro");
        }
        return response;
    }

    @Override
    public String expenseUpdated(Long id, ExpenseRequestDto expenseRequestDto) throws DAOException {
        String response= "Registro actualizado";
        Expense expense= mapDtoToExpense(expenseRequestDto);
        Integer responseUpdated= expenseRepository.update(id, expense);
        if (responseUpdated.equals(0)) {
            System.out.println("No se actualizó el registro");
        }
        return response;
    }

    private Expense mapDtoToExpense(ExpenseRequestDto expenseRequestDto){
        Expense expense= new Expense();
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setDate(expenseRequestDto.getDate());
        expense.setCategoryName(expenseRequestDto.getCategoryRequestDto().getName());
        return expense;
    }

    private ExpenseResponseDto  ExpenseToExpenseResponseDto (Expense expense){
        ExpenseResponseDto expenseResponseDto= new ExpenseResponseDto();
        expenseResponseDto.setAmount(expense.getAmount());
        ExpenseCategoryResponseDto expenseCategoryResponseDto= new ExpenseCategoryResponseDto();
        expenseCategoryResponseDto.setId(expense.getId());
        expenseCategoryResponseDto.setName(expense.getCategoryName());
        expenseResponseDto.setExpenseCategoryResponseDto(expenseCategoryResponseDto);
        expenseResponseDto.setDate(expense.getDate());
        return expenseResponseDto;
    }
}
