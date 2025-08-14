package org.library.library.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ISBN;

public record BookDto(@NotEmpty(message = "В предоставленных данных отсутствует название") String title,
                      @NotEmpty(message = "В предоставленных данных отсутствует автор") String author,
                      @NotEmpty(message = "В предоставленных данных отсутствует номер")@ISBN String isbn,
                      @NotNull(message = "В предоставленных данных отсутствует год выпуска")
                      @NotNull(message = "В предоставленных данных отсутствует год выпуска книги") Integer publicationYear,
                      @NotNull(message = "В предоставленных данных отсутствует число копий книги") Integer totalCopies,
                      @NotNull(message = "В предоставленных данных отсутствует число доступных копий книги") @Min(0) Integer availableCopies) {
}
