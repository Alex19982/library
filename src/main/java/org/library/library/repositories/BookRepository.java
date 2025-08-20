package org.library.library.repositories;

import org.library.library.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//примени автовыравнивание кода
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> getBooksByTitleAndAuthor(String title, String author);

    List<Book> getBooksByPublicationYear(Integer publicationYear);

}
