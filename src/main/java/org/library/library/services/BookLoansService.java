package org.library.library.services;

import org.library.library.entites.BookLoans;
import org.library.library.entites.Reader;
import org.library.library.repositories.BookRepository;
import org.library.library.repositories.LoanRepository;
import org.library.library.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BookLoansService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public BookLoansService(LoanRepository loanRepository, BookRepository bookRepository, ReaderRepository readerRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public void loanBook(BookLoans bookLoans) {
        bookLoans.setLoanDate(LocalDate.now());
        loanRepository.save(bookLoans);
       var entity2=bookRepository.findById(bookLoans.getBookId()).get();
       entity2.issueBook();
       bookRepository.save(entity2);
    }

    public List<BookLoans> getAllLoans() {
        return loanRepository.findAll();
    }


    public void returnBook(Integer id) {
        var loan = loanRepository.findById(id).get();
        loan.setStatus("Возвращено");
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book=bookRepository.findById(loan.getBookId()).get();
        book.returnBook();
        bookRepository.save(book);

    }

    public List<BookLoans> getAllReaderLoans(Integer readerId) {
       return loanRepository.findAllByReaderId(readerId);
    }

    public List<BookLoans> getOverdueLoans() {
        return  loanRepository.findAllByStatus("Просрочено");
    }

    public List<Reader> getDebtors() {
        return getOverdueLoans().stream().map(BookLoans::getReaderId).map(x->readerRepository.findById(x).get()).toList();
    }

    public void overdueBook(Integer id) {
        var loan = loanRepository.findById(id).get();
        loan.setStatus("Просрочено");
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book=bookRepository.findById(loan.getBookId()).get();
        book.returnBook();
        bookRepository.save(book);
    }
}
