package org.library.library.services;

import org.library.library.dto.BookLoanDto;
import org.library.library.entites.BookLoans;
import org.library.library.entites.Reader;
import org.library.library.exepcion.ConflictException;
import org.library.library.exepcion.ResourceNotFoundException;
import org.library.library.mappers.BookLoanMapper;
import org.library.library.repositories.BookRepository;
import org.library.library.repositories.LoanRepository;
import org.library.library.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void loanBook(BookLoanDto bookLoanDto) {
        if (bookRepository.findById(bookLoanDto.bookId()).orElseThrow().getAvailableCopies() == 0) {
            throw new ConflictException("Все копии книги уже выданы");
        }
        var entity = new BookLoans();
        var book = bookRepository.findById(bookLoanDto.bookId()).orElseThrow(() -> new ResourceNotFoundException("Книги с id "
                +bookLoanDto.bookId()+" не существует"));
        var reader = readerRepository.findById(bookLoanDto.readerId()).orElseThrow(() -> new ResourceNotFoundException("Читателя с id "
                +bookLoanDto.readerId()+" не существует"));
        BookLoanMapper.map(bookLoanDto, entity, book,reader);
        entity.setLoanDate(LocalDate.now());
        entity.getBook().issueBook();
        loanRepository.save(entity);
    }

    public List<BookLoans> getAllLoans() {
        return loanRepository.findAll();
    }


    public void returnBook(Integer id) {
        var loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Долга с id " + id + " не существует"));
        loan.returnBook();
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book = bookRepository.findById(loan.getBook().getId()).
                orElseThrow(() -> new ResourceNotFoundException("Книги с id " + loan.getBook().getId() + " не существует"));
        book.returnBook();
        bookRepository.save(book);

    }

    public List<BookLoans> getAllReaderLoans(Integer readerId) {
        return loanRepository.findAllByReaderId(readerId);
    }

    public List<BookLoans> getOverdueLoans() {
        return loanRepository.findAllByStatus("Просрочено");
    }

    public List<Reader> getDebtors() {
        return getOverdueLoans().stream().map(BookLoans::getReader).toList();
    }

    public void expiredBook(Integer id) {
        var loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Долга с id " + id + " не существует"));
        loan.expiredBook();
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book = bookRepository.findById(loan.getBook().getId()).
                orElseThrow(() -> new ResourceNotFoundException("Книги с id " + loan.getBook().getId() + " не существует"));
        book.returnBook();
        bookRepository.save(book);
    }

    public BookLoans getById(Integer id) {
        return loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Долга с id " + id + " не существует"));
    }
}
