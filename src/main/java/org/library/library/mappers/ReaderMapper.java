package org.library.library.mappers;

import org.library.library.dto.ReaderDto;
import org.library.library.entites.Reader;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReaderMapper {

    // DTO -> Entity
    Reader toEntity(ReaderDto dto);

    // Частичное обновление: null из DTO НЕ перезаписывает существующие значения
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ReaderDto dto, @MappingTarget Reader target);

    // Небольшая нормализация строк (по желанию)
    @AfterMapping
    default void normalize(@MappingTarget Reader target) {
        if (target.getFirstName() != null) target.setFirstName(target.getFirstName().trim());
        if (target.getLastName() != null) target.setLastName(target.getLastName().trim());
        if (target.getEmail() != null) target.setEmail(target.getEmail().trim().toLowerCase());
        if (target.getPhone() != null) target.setPhone(target.getPhone().replaceAll("\\s+", ""));
    }
}