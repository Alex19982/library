package org.library.library.services;

import org.library.library.entites.Book;
import org.library.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).orElseThrow(()-> new IllegalStateException(id+" not found"));
    }

    public void updateBook(Integer id, Book book) {
        Book entity=getById(id);
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setIsbn(book.getIsbn());
        entity.setAvailableCopies(book.getAvailableCopies());
        entity.setPublicationYear(book.getPublicationYear());
        entity.setTotalCopies(book.getTotalCopies());
        bookRepository.save(entity);
    }

    public List<Book> getByTitleAndAuthor(String title, String author) {
        return bookRepository.getBooksByTitleAndAuthor(title,author);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByYear(Integer year) {
        return bookRepository.getBooksByPublicationYear(year);
    }
}
