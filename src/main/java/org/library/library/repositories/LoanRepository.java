package org.library.library.repositories;

import org.library.library.entites.BookLoans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<BookLoans,Integer> {
    List<BookLoans> findAllByReaderId(Integer readerId);


    List<BookLoans> findAllByStatus(String просрочено);
}
