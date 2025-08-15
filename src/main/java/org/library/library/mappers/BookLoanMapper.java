package org.library.library.mappers;

import org.library.library.dto.BookLoanDto;
import org.library.library.entites.Book;
import org.library.library.entites.BookLoans;
import org.library.library.entites.Reader;

public class BookLoanMapper {
    public static void map(BookLoanDto bookLoanDto, BookLoans  bookLoans, Book book, Reader reader){
        bookLoans.setLoanDate(bookLoanDto.loanDate());
        bookLoans.setDueDate(bookLoanDto.dueDate());
        bookLoans.setReturnDate(bookLoanDto.returnDate());
        bookLoans.setStatus(bookLoanDto.status());
        bookLoans.setReader(reader);
        bookLoans.setBook(book);
    }
}
