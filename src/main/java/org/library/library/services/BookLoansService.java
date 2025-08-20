package org.library.library.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.library.library.dto.BookLoanDto;
import org.library.library.entites.Book;
import org.library.library.entites.BookLoan;
import org.library.library.entites.Reader;
import org.library.library.excepcion.ConflictException;
import org.library.library.excepcion.ResourceNotFoundException;
import org.library.library.mappers.BookLoanMapper;
import org.library.library.repositories.BookRepository;
import org.library.library.repositories.LoanRepository;
import org.library.library.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLoansService { //Автовыравнивание кода
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final BookLoanMapper bookLoanMapper;

    @Transactional
    public void loanBook(BookLoanDto dto) {
        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new ResourceNotFoundException("Книги с id " + dto.bookId() + " не существует"));
        if (book.getAvailableCopies() <= 0) {
            throw new ConflictException("Все экземпляры книги уже выданы");
        }
        Reader reader = readerRepository.findById(dto.readerId())
                .orElseThrow(() -> new ResourceNotFoundException("Читателя с id " + dto.readerId() + " не существует"));
        BookLoan loan = bookLoanMapper.toEntity(dto);
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoanDate(LocalDate.now());
        loan.giveBook();
        book.issueBook();
        loanRepository.save(loan);
    }

    @Transactional
    public List<BookLoan> getAllLoans() {
        return loanRepository.findAll(); //Пагинация
    }

    //Что если мы сохранили loanRepository.save(loan); возврат книги, но упали на   bookRepository.save(book);
    //Данные будут неконсистентными. Долг возвращен, а книга не вернулась на полку. Как пофиксить?
    public void returnBook(Integer id) {
        var loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Долга с id " + id + " не существует")); //formatted()
        loan.returnBook();
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book = bookRepository.findById(loan.getBook().getId()).
                orElseThrow(() -> new ResourceNotFoundException("Книги с id " + loan.getBook().getId() + " не существует")); //formatted()
        book.returnBook();
        bookRepository.save(book);

    }

    public List<BookLoan> getAllReaderLoans(Integer readerId) {
        return loanRepository.findAllByReaderId(readerId);
    }

    public List<BookLoan> getOverdueLoans() {
        return loanRepository.findAllByStatus(BookLoan.Status.EXPIRED);
    } //Статусы в enum

    public List<Reader> getDebtors() {
        return getOverdueLoans().stream()
                .map(BookLoan::getReader)
                .toList(); //обычно при написании стримов каждая новая точка после .stream() пишется с новой строки
    }

    //Аналогично. Как мы можем решить проблему, что лоан обновился, а на сохранении книги упали. Нарушится консистентность
    @Transactional
    public void expiredBook(Integer id) {
        var loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Долга с id " + id + " не существует")); //formatted()
        loan.expiredBook();
        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);
        var book = bookRepository.findById(loan.getBook().getId()).
                orElseThrow(() -> new ResourceNotFoundException("Книги с id " + loan.getBook().getId() + " не существует")); //formatted()
        book.returnBook();
        bookRepository.save(book);
    }

    public BookLoan getById(Integer id) {
        return loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Долга с id " + id + " не существует")); //formatted()
    }
}
