package org.library.library.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.UniqueElements;
import org.library.library.validation.Uniq;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    @ISBN
    @Uniq
    private String isbn;
    @NotNull
    private Integer publicationYear;
    @NotNull
    private Integer totalCopies;
    @NotNull
    @Min(0)
    private Integer availableCopies;

    public void issueBook(){
        availableCopies--;
    }
    public void returnBook(){
        availableCopies++;
    }
    public void updateBook(Book book){
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.publicationYear = book.getPublicationYear();
        this.totalCopies = book.getTotalCopies();
        this.availableCopies = book.getAvailableCopies();
    }

}
