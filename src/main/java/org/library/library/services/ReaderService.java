package org.library.library.services;

import org.library.library.dto.ReaderDto;
import org.library.library.entites.Reader;
import org.library.library.exepcion.ConflictException;
import org.library.library.mappers.ReaderMapper;
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

    public void addReader(ReaderDto reader) {
        if(getByEmail(reader.email())!=null){
            throw new ConflictException("Email already exists");
        }
        var entity = new Reader();
        ReaderMapper.map(reader, entity);
        entity.setRegistrationDate(LocalDate.now());
        readerRepository.save(entity);
    }

    public void updateReader(Integer id, ReaderDto reader) {
        Reader entity=getById(id);
        ReaderMapper.map(reader,entity);
        readerRepository.save(entity);
    }

    public void deleteReaderById(Integer id) {
        readerRepository.deleteById(id);
    }
    public Reader getByEmail(String email) {
        return readerRepository.findByEmail(email);
    }
}
