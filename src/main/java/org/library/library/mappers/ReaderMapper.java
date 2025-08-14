package org.library.library.mappers;

import org.library.library.dto.ReaderDto;
import org.library.library.entites.Reader;

public class ReaderMapper {
    public static void map(ReaderDto dto, Reader reader){
        reader.setFirstName(dto.firstName());
        reader.setLastName(dto.lastName());
        reader.setEmail(dto.email());
        reader.setPhone(dto.phone());
        reader.setRegistrationDate(dto.registrationDate());
    }
}
