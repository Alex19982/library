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

    public void loanBook(BookLoanDto bookLoans) {
        if(bookRepository.findById(bookLoans.bookId()).orElseThrow().getAvailableCopies()==0){
            throw new ConflictException("Все копии книги уже выданы");
        }
        var entity = new BookLoans();
        BookLoanMapper.map(bookLoans, entity);
        entity.setLoanDate(LocalDate.now());
        entity.getBook();
        loanRepository.save(entity);
       var entity2=bookRepository.findById(entity.getBookId()).orElseThrow(()-> new ResourceNotFoundException("Книги с id "+entity.getBookId()+" не существует"));
       entity2.issueBook();
       bookRepository.save(entity2);
    }

    public List<BookLoans> getAllLoans() {
        return loanRepository.findAll();
    }


    public void returnBook(Integer id) {
        var loan = loanRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Долга с id "+id+" не существует"));
        loan.returnBook();
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book=bookRepository.findById(loan.getBookId()).orElseThrow(()-> new ResourceNotFoundException("Книги с id "+loan.getBookId()+" не существует"));
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
        return getOverdueLoans().stream().map(BookLoans::getReaderId).map(x->readerRepository.findById(x).
                orElseThrow(()-> new ResourceNotFoundException("Книги нет"))).toList();
    }

    public void expiredBook(Integer id) {
        var loan = loanRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Долга с id "+id+" не существует"));
        loan.expiredBook();
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book=bookRepository.findById(loan.getBookId()).orElseThrow(()-> new ResourceNotFoundException("Книги с id "+loan.getBookId()+" не существует"));
        book.returnBook();
        bookRepository.save(book);
    }

    public BookLoans getById(Integer id) {
        return loanRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Долга с id "+id+" не существует"));
    }
}
