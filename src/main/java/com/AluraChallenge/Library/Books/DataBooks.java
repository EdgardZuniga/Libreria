package com.AluraChallenge.Library.Books;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBooks(
        @JsonAlias("id") Integer Id,
        @JsonAlias("title") String Title,
        @JsonAlias("authors") List<DataAuthors> Authors,
        @JsonAlias("languages") List<String> Language,
        @JsonAlias("download_count") Integer Downloads
) {
}
