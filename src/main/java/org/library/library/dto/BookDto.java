package org.library.library.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ISBN;

//Я бы не делал сообщения такими длинными. Если несколько полей невалидны, то силшком много дублирующейса инфо будет в сообщении.
//Можно ограничиться "То-то то-то должно быть заполнено или не должно быть пустым". Как у тебя же сделано в ReaderDto
public record BookDto(@NotEmpty(message = "Отсутствует название") String title,
                      @NotEmpty(message = "Отсутствует автор") String author,
                      @NotEmpty(message = "Номер ISBN отсутствует ") @ISBN String isbn,
                      @NotNull(message = "Год выпуска отсутствует") Integer publicationYear,
                      @NotNull(message = "Число копий книги отсутствует") Integer totalCopies,
                      @NotNull(message = "Число свободных копий отсутствует") @Min(0) Integer availableCopies) {
}
