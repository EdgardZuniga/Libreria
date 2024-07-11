package com.AluraChallenge.Library.Repository;

import com.AluraChallenge.Library.Model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<AuthorModel, Long> {
    Optional<AuthorModel> findByName(String name);
}
