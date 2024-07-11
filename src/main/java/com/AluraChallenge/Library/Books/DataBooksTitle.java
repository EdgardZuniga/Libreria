package com.AluraChallenge.Library.Books;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBooksTitle(
        @JsonAlias("results") List<DataBooks> result
) {
}
