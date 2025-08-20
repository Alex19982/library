package org.library.library.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.library.library.dto.ReaderDto;
import org.library.library.entites.Reader;
import org.library.library.excepcion.ConflictException;
import org.library.library.mappers.ReaderMapper;
import org.library.library.repositories.ReaderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getById(Integer id) {
        return readerRepository.findById(id).orElseThrow(() -> new IllegalStateException("%s Not found".formatted(id))); //formatted()
    }

    @Transactional
    public void addReader(ReaderDto dto) {
        if (getByEmail(dto.email()) != null) {
            throw new ConflictException("Email already exists");
        }
        Reader entity = readerMapper.toEntity(dto);
        entity.setRegistrationDate(LocalDate.now());
        readerRepository.save(entity);
    }

    @Transactional
    public void updateReader(Integer id, ReaderDto dto) {
        Reader entity = getById(id);
        readerMapper.updateFromDto(dto, entity);
        readerRepository.save(entity);
    }

    public void deleteReaderById(Integer id) {
        readerRepository.deleteById(id);
    }

    public Reader getByEmail(String email) {
        return readerRepository.findByEmail(email);
    }
}
