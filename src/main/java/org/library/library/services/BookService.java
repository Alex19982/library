package org.library.library.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.library.library.dto.BookDto;
import org.library.library.entites.Book;
import org.library.library.excepcion.ResourceNotFoundException;
import org.library.library.mappers.BookMapper;
import org.library.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService { //Автовыравнивание кода
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public void addBook(BookDto dto) {
        Book entity = bookMapper.toEntity(dto);
        bookRepository.save(entity);
    }

    public Book getById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Книги с id " + id + " не существует")); //formatted()
    }

    @Transactional
    public void updateBook(Integer id, BookDto dto) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Книги с id " + id + " не существует"));
        bookMapper.updateFromDto(dto, entity);
        bookRepository.save(entity);
    }

    public List<Book> getByTitleAndAuthor(String title, String author) {
        return bookRepository.getBooksByTitleAndAuthor(title, author);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getBooksByYear(Integer year) {
        var books = bookRepository.getBooksByPublicationYear(year);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("Книг с годом " + year + " не существует"); //formatted()
        }
        return bookRepository.getBooksByPublicationYear(year);
    }

}
