package org.library.library.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;


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
    @Column(unique = true)
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

}
