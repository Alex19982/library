package org.library.library.controllers;

import jakarta.validation.Valid;
import org.library.library.entites.BookLoans;
import org.library.library.entites.Reader;
import org.library.library.services.BookLoansService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/loans")
public class BookLoansController {
    BookLoansService bookLoansService;

    public BookLoansController(BookLoansService bookLoansService) {
        this.bookLoansService = bookLoansService;
    }

    @PostMapping
    public String bookLoans(@Valid @RequestBody BookLoans bookLoans) {
        bookLoansService.loanBook(bookLoans);
        return "Дата возврата "+bookLoans.getDueDate();
    }
    @GetMapping("/get")
    public List<BookLoans> getBookLoans() {
        return bookLoansService.getAllLoans();
    }
    @PutMapping("/return/{id}")
    public void returnBook(@PathVariable Integer id) {
        bookLoansService.returnBook(id);
    }
    @GetMapping("/reader/{readerId}")
    public List<BookLoans> getReaderLoans(@PathVariable Integer readerId) {
        return bookLoansService.getAllReaderLoans(readerId);
    }
    @GetMapping("/overdue")
    public List<BookLoans> getOverdueBookLoans() {
        return bookLoansService.getOverdueLoans();
    }
    @GetMapping("/overdue/list")
    public List<Reader> getOverdueBookLoansList() {
        return bookLoansService.getDebtors();
    }
    @PutMapping("overdue/{id}")
    public void overdueBook(@PathVariable Integer id) {
        bookLoansService.overdueBook(id);
    }
}
