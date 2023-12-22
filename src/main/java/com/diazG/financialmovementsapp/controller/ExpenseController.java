package com.diazG.financialmovementsapp.controller;

import com.diazG.financialmovementsapp.dto.request.ExpenseRequestDto;
import com.diazG.financialmovementsapp.dto.response.ExpenseResponseDto;
import com.diazG.financialmovementsapp.exceptions.DAOException;
import com.diazG.financialmovementsapp.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<String> createExpenseHandler(@RequestBody ExpenseRequestDto expenseRequestDto){
        String response = expenseService.createExpense(expenseRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpensesHandler() throws DAOException {
        List<ExpenseResponseDto> response= expenseService.selectAllExpensesDto();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> getOneExpense(@PathVariable Long id) throws DAOException {
        ExpenseResponseDto response= expenseService.selectExpenseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) throws DAOException {
        String response = expenseService.expenseDeleted(id);
        return ResponseEntity.status(HttpStatus.GONE).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequestDto expenseRequestDto) throws DAOException {
        String response = expenseService.expenseUpdated(id, expenseRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
