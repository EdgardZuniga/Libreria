package com.AluraChallenge.Library.Books;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthors(
        @JsonAlias("birth_year") Integer BirthYear,
        @JsonAlias("death_year") Integer DeathYear,
        @JsonAlias("name") String Name
) {
}
