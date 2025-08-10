package org.library.library.services;

import org.library.library.entites.Reader;
import org.library.library.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReaderService {
    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getById(Integer id) {
        return readerRepository.findById(id).orElseThrow(()->new IllegalStateException(id+"Not found"));
    }

    public void addReader(Reader reader) {
        reader.setRegistrationDate(LocalDate.now());
        readerRepository.save(reader);
    }

    public void updateReader(Integer id, Reader reader) {
        Reader entity=getById(id);
        entity.update(reader);
        readerRepository.save(entity);
    }

    public void deleteReaderById(Integer id) {
        readerRepository.deleteById(id);
    }
}
