package org.library.library.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record BookLoanDto (@NotNull(message = "Отсутствует id книги") Integer bookId,
                           @NotNull(message = "Отсутствует id читателя") Integer readerId,
                           @PastOrPresent(message = "Дата выдачи не должна находиться в будущем")  LocalDate loanDate,
                           @Future(message ="Дата возврата должна находиться находиться в будущем" )
                           @NotNull(message = "Дата возврата должна присутствовать") LocalDate dueDate,
                           @FutureOrPresent(message ="Дата возврата должна находиться находиться в будущем") LocalDate returnDate,String status){
}
