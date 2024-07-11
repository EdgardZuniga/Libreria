package com.AluraChallenge.Library.Repository;

import com.AluraChallenge.Library.Model.AuthorModel;
import com.AluraChallenge.Library.Model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<BookModel, Long> {

}

