package org.library.library.mappers;

import org.library.library.dto.BookDto;
import org.library.library.entites.Book;

public class BookMapper {
    public static void map(BookDto bookDto, Book book) {
        book.setTitle(bookDto.title());
        book.setAuthor(bookDto.author());
        book.setIsbn(bookDto.isbn());
        book.setPublicationYear(bookDto.publicationYear());
        book.setTotalCopies(bookDto.totalCopies());
        book.setAvailableCopies(bookDto.availableCopies());
    }
}
