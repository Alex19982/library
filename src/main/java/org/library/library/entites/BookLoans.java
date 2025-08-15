package org.library.library.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookLoans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "BOOK_ID",referencedColumnName = "ID")
    @NotNull
    private Book book;
    @OneToOne
    @JoinColumn(name="Reader_id",referencedColumnName = "ID")
    @NotNull
    private Reader reader;
    @PastOrPresent
    private LocalDate loanDate;
    @Future
    @NotNull
    private LocalDate dueDate;
    @FutureOrPresent
    private LocalDate returnDate;
    private String status;

    public void giveBook(){
        status="Выдано";
    }
    public void returnBook(){
        status="Возвращено";
    }
    public void expiredBook(){
        status="Просрочено";
    }
}