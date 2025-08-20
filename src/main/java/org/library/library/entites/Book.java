package org.library.library.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter//Эта аннотация включает в себя equals и hashcode, поэтому нельзя юзать ее на Entity классах
//вот что можно почитать https://jpa-buddy.com/blog/hopefully-the-final-article-about-equals-and-hashcode-for-jpa-entities-with-db-generated-ids/
public class Book { //Автовыравнивание кода
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

    public void issueBook() { //Как улучшить
        availableCopies--;
    }

    public void returnBook() {//Как улучшить
        availableCopies++;
    }

}
