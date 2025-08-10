package org.library.library.controllers;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.library.library.entites.Reader;
import org.library.library.services.ReaderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/readers")
public class ReaderController {
    private final ReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }
    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable Integer id) {
        return readerService.getById(id);
    }
    @PostMapping
    public void createReader(@Valid @RequestBody Reader reader) {
        readerService.addReader(reader);
    }
    @PutMapping("/{id}")
    public void updateReader(@PathVariable Integer id,@Valid @RequestBody Reader reader) {
        readerService.updateReader(id,reader);
    }
    @DeleteMapping("/{id}")
    public void deleteReaderById(@PathVariable Integer id) {
        readerService.deleteReaderById(id);
    }
}
