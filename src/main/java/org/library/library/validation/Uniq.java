package org.library.library.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqValidator.class)
public @interface Uniq {
    String message() default "Такой ИСБН уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
