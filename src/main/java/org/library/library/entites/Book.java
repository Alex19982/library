package org.library.library.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.ISBN;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
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
    private String isbn;
    @NotNull
    private Integer publicationYear;
    @NotNull
    private Integer totalCopies;
    @NotNull
    private AtomicInteger availableCopies;

    public Book(Integer id, String title, String author, String isbn, Integer publicationYear, Integer totalCopies, AtomicInteger availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public Book() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public AtomicInteger getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(AtomicInteger availableCopies) {

        this.availableCopies = availableCopies;
    }

    public void issueBook(){
        availableCopies.addAndGet(-1);
    }
    public void returnBook(){
        availableCopies.addAndGet(1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(publicationYear, book.publicationYear) && Objects.equals(totalCopies, book.totalCopies) && Objects.equals(availableCopies, book.availableCopies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, publicationYear, totalCopies, availableCopies);
    }
}
