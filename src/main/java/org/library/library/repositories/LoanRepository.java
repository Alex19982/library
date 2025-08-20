package org.library.library.repositories;

import org.library.library.entites.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LoanRepository extends JpaRepository<BookLoan, Integer> {
    List<BookLoan> findAllByReaderId(Integer readerId);

    List<BookLoan> findAllByStatus(BookLoan.Status status);


}
