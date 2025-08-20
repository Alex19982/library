package org.library.library.mappers;

import org.library.library.dto.BookLoanDto;
import org.library.library.entites.Book;
import org.library.library.entites.BookLoan;
import org.library.library.entites.Reader;
import org.mapstruct.*;

//Во всех мапперах нужно создавать заполняемую сущность и возвращать ее из маппера как результат вместо передачи в метод. На 1 параметр меньше
@Mapper(componentModel = "spring")
public interface BookLoanMapper {
    @Mappings({
            @Mapping(source = "book.id", target = "bookId"),
            @Mapping(source = "reader.id", target = "readerId"),
            // Вытаскиваем имя статуса, не ссылаясь на тип enum
            @Mapping(target = "status", expression = "java(loan.getStatus() == null ? null : loan.getStatus().name())")
    })
    BookLoanDto toDto(BookLoan loan);

    @Mappings({
            // Собираем заглушки Book/Reader только по id
            @Mapping(source = "bookId", target = "book", qualifiedByName = "bookFromId"),
            @Mapping(source = "readerId", target = "reader", qualifiedByName = "readerFromId"),

            // Статус задаётся бизнес-методами, поэтому игнорируем
            @Mapping(target = "status", ignore = true)
    })
    BookLoan toEntity(BookLoanDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(source = "bookId", target = "book", qualifiedByName = "bookFromId"),
            @Mapping(source = "readerId", target = "reader", qualifiedByName = "readerFromId"),
            @Mapping(target = "status", ignore = true)
    })
    void updateFromDto(BookLoanDto dto, @MappingTarget BookLoan target);

    @Named("bookFromId")
    default Book bookFromId(Integer id) {
        if (id == null) return null;
        Book b = new Book();
        b.setId(id);
        return b;
    }

    @Named("readerFromId")
    default Reader readerFromId(Integer id) {
        if (id == null) return null;
        Reader r = new Reader();
        r.setId(id);
        return r;
    }

    /*
    Задача со звездочкой (если интересно). Можешь попробовать разобраться в библиотеке для маппинга
    Сейчас так делают на проектах
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
    </dependency>
     */
}
