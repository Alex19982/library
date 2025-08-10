package org.library.library.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.library.library.repositories.BookRepository;
import org.library.library.services.BookLoansService;
import org.library.library.services.BookService;
public class UniqValidator implements ConstraintValidator<Uniq,String> {
   //private final BookService bookService;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return s != null ;
    }

}
