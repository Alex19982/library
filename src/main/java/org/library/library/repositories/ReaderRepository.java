package org.library.library.repositories;

import org.library.library.entites.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

//примени автовыравнивание кода
public interface ReaderRepository extends JpaRepository<Reader,Integer> {
    Reader findByEmail(String email);

}
