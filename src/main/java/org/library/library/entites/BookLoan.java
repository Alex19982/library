package org.library.library.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter//Аналогично как в book
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")
    @NotNull
    private Book book;
    @OneToOne
    @JoinColumn(name = "Reader_id", referencedColumnName = "ID")
    @NotNull
    private Reader reader;
    @PastOrPresent
    private LocalDate loanDate;
    @Future
    @NotNull
    private LocalDate dueDate;
    @FutureOrPresent
    private LocalDate returnDate;

    public enum Status {
        GIVEAWAY, RETURN, EXPIRED
    }

    @Enumerated(EnumType.STRING)
    private Status status;

    public void giveBook() {
        status = Status.GIVEAWAY;
    }

    public void returnBook() {
        status = Status.RETURN;
    }

    public void expiredBook() {
        status = Status.EXPIRED;
    }
}