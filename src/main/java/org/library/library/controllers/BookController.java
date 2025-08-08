package org.library.library.controllers;

import jakarta.validation.Valid;
import org.library.library.services.BookService;
import org.library.library.entites.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }
    @PostMapping
    public void addNewBook(@Valid @RequestBody Book book) {
        bookService.addBook(book);
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getById(id);
    }
    @PutMapping("/{id}")
    public void updateBookById(@PathVariable Integer id,@Valid @RequestBody Book book) {
        bookService.updateBook(id,book);
    }
    @GetMapping("/search")
    public List<Book> getBooksByTitleAndAuthor(@RequestParam (value = "title")String title,@RequestParam(value = "author") String author) {
        return bookService.getByTitleAndAuthor(title,author);
    }
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Integer id) {
        bookService.deleteById(id);
    }
    @GetMapping("/search/{year}")
    public List<Book> getBooksByYear(@PathVariable Integer year) {
        return bookService.getBooksByYear(year);
    }
}
