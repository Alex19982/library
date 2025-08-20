package org.library.library.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.library.dto.ReaderDto;
import org.library.library.entites.Reader;
import org.library.library.services.ReaderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/readers")
public class ReaderController {//Автовыравнивание кода
    private final ReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders() {
        log.trace("getAllReaders{}", readerService.getAllReaders());
        return readerService.getAllReaders();
    } //Добвить пустые строки между методами

    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable Integer id) {
        log.trace("getReaderById{}", id);
        return readerService.getById(id);
    }

    @PostMapping
    public void createReader(@Valid @RequestBody ReaderDto reader) {
        log.trace("createReader{}", reader);
        readerService.addReader(reader);
    }

    @PutMapping("/{id}")
    public void updateReader(@PathVariable Integer id, @Valid @RequestBody ReaderDto reader) {
        log.trace("updateReader{}", reader);
        readerService.updateReader(id, reader);
    }

    @DeleteMapping("/{id}")
    public void deleteReaderById(@PathVariable Integer id) {
        log.trace("deleteReaderById{}", id);
        readerService.deleteReaderById(id);
    }
}
