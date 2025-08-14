package org.library.library.services;

import org.library.library.dto.BookDto;
import org.library.library.entites.Book;
import org.library.library.exepcion.ResourceNotFoundException;
import org.library.library.mappers.BookMapper;
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

    public void addBook(BookDto book) {
        var bookEntity = new Book();
        BookMapper.map(book, bookEntity);
        bookRepository.save(bookEntity);
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Книги с id "+id+" не существует"));
    }

    public void updateBook(Integer id, BookDto book) {
        Book entity=getById(id);
        BookMapper.map(book,entity);
        bookRepository.save(entity);
    }

    public List<Book> getByTitleAndAuthor(String title, String author) {
        return bookRepository.getBooksByTitleAndAuthor(title,author);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByYear(Integer year) {
        var entity=bookRepository.getBooksByPublicationYear(year);
        if(entity.isEmpty()){
            throw new ResourceNotFoundException("Книг с годом " + year+" не существует");
        }
        return bookRepository.getBooksByPublicationYear(year);
    }

}
