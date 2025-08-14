package org.library.library.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.library.adapter.Adapter;
import org.library.library.adapter.AdapterImpl;
import org.library.library.dto.BookDto;
import org.library.library.services.BookService;
import org.library.library.entites.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/books")
public class BookController {

    BookService bookService;
    Adapter adapter;
    @GetMapping
    public List<Book> getBooks() {
        log.info("getBooks()");
        return bookService.getAllBooks();

    }
    @PostMapping
    public void addNewBook(@Valid @RequestBody BookDto book) {
        log.trace("addNewBook({})", book);
        bookService.addBook(book);
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        log.trace("getBookById({})", id);
        return bookService.getById(id);
    }
    @PutMapping("/{id}")
    public void updateBookById(@PathVariable Integer id,@Valid @RequestBody BookDto book) {
        log.trace("updateBookById({}, {})", id, book);
        bookService.updateBook(id,book);
    }
    @GetMapping("/search")
    public List<Book> getBooksByTitleAndAuthor(@RequestParam (value = "title")String title,@RequestParam(value = "author") String author) {
        log.trace("getBooksByTitleAndAuthor({}, {})", title, author);
        return bookService.getByTitleAndAuthor(title,author);
    }
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Integer id) {
        log.trace("deleteBookById({})", id);
        bookService.deleteById(id);
    }
    @GetMapping("/search/{year}")
    public List<Book> getBooksByYear(@PathVariable Integer year) {
        log.trace("getBooksByYear({})", year);
        return bookService.getBooksByYear(year);
    }

}
