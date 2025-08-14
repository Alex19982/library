package org.library.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ReaderDto (@NotEmpty(message = "Фамилия читателя не должна бы пустой") String firstName,
                         @NotEmpty(message = "Имя читателя не должно бы пустой") String lastName,
                         @Email(message = "Некорректный почтовый адрес") String email,String phone,
                         @PastOrPresent(message = "Дата регистрации должна быть в прошлом") LocalDate registrationDate){
}
