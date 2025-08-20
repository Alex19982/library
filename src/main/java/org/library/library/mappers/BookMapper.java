package org.library.library.mappers;

import org.library.library.dto.BookDto;
import org.library.library.entites.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

//Во всех мапперах нужно создавать заполняемую сущность и возвращать ее из маппера как результат вместо передачи в метод. На 1 параметр меньше
@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(BookDto dto, @MappingTarget Book target);
}