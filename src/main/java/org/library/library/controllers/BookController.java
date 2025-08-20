package org.library.library.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.library.dto.BookDto;
import org.library.library.entites.Book;
import org.library.library.excepcion.BadRequestException;
import org.library.library.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/books")
public class BookController { //Сделай автовыравнивание кода (заучи сочетание клавиш)

    private final BookService bookService; //добавить private final


    @GetMapping
    public List<Book> getBooks() {
        log.info("getBooks()");
        return bookService.getAllBooks();

    } //Добавь пустую строку между методами

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
    public void updateBookById(@PathVariable Integer id, @Valid @RequestBody BookDto book) {
        log.trace("updateBookById({}, {})", id, book);
        bookService.updateBook(id, book);
    }

    @GetMapping("/search") //Не обязательно указывать value в request param, если название параметра такое же
    public List<Book> getBooksByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        log.trace("getBooksByTitleAndAuthor({}, {})", title, author);
        return bookService.getByTitleAndAuthor(title, author);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Integer id) {
        log.trace("deleteBookById({})", id);
        bookService.deleteById(id);
    }

    @GetMapping("/search/{year}")
    public List<Book> getBooksByYear(@PathVariable Integer year) { //Можно добавить валидацию, чтобы хотя бы отризательные числа нельзя было ну и к ниги раньше какого года наврядли печатались?)
        log.trace("getBooksByYear({})", year);
        if (year < 850) {
            throw new BadRequestException("В этом году еще не издавались книги");
        }
        return bookService.getBooksByYear(year);
    }

}
