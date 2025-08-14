package org.library.library.mappers;

import org.library.library.dto.BookLoanDto;
import org.library.library.entites.BookLoans;

public class BookLoanMapper {
    public static void map(BookLoanDto bookLoanDto, BookLoans  bookLoans){
        bookLoans.setLoanDate(bookLoanDto.loanDate());
        bookLoans.setDueDate(bookLoanDto.dueDate());
        bookLoans.setReturnDate(bookLoanDto.returnDate());
        bookLoans.setStatus(bookLoanDto.status());
        bookLoans.setBookId(bookLoanDto.bookId());
        bookLoans.setReaderId(bookLoanDto.readerId());
    }
}
