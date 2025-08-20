package org.library.library.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.library.dto.BookLoanDto;
import org.library.library.entites.BookLoan;
import org.library.library.entites.Reader;
import org.library.library.services.BookLoansService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/loans")
public class BookLoansController { //Сделай автовыравнивание кода
    private final BookLoansService bookLoansService; //добавить private final

    @PostMapping
    public String getBook(@Valid @RequestBody BookLoanDto bookLoans) {
        log.info("BookLoan{}", bookLoans);
        bookLoansService.loanBook(bookLoans);
        return "Дата возврата " + bookLoans.dueDate();
    } //Пустую строку добавить между методами

    @GetMapping("/get")
    public List<BookLoan> getBookLoans() {
        log.trace("getBookLoans");
        return bookLoansService.getAllLoans();
    }

    @PutMapping("/return/{id}")
    public void returnBook(@PathVariable Integer id) {
        log.trace("returnBook{}", id);
        bookLoansService.returnBook(id);
    }

    @GetMapping("/reader/{readerId}")
    public List<BookLoan> getReaderLoans(@PathVariable Integer readerId) {
        log.trace("getReaderLoans{}", readerId);
        return bookLoansService.getAllReaderLoans(readerId);
    }

    @GetMapping("/overdue")
    public List<BookLoan> getOverdueBookLoans() {
        log.trace("getOverdueBookLoans");
        return bookLoansService.getOverdueLoans();
    }

    @GetMapping("/overdue/list")
    public List<Reader> getOverdueBookLoansList() {
        log.trace("getOverdueBookLoansList");
        return bookLoansService.getDebtors();
    }

    @PutMapping("overdue/{id}")
    public void overdueBook(@PathVariable Integer id) {
        log.trace("overdueBook{}", id);
        bookLoansService.expiredBook(id);
    }

    @GetMapping("/{id}")
    public BookLoan getBookLoansById(@PathVariable Integer id) {
        log.trace("getBookLoansById{}", id);
        return bookLoansService.getById(id);
    }
}
