package com.AluraChallenge.Library.Model;

import com.AluraChallenge.Library.Books.DataAuthors;
import com.AluraChallenge.Library.Books.DataBooks;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Libros")
public class BookModel {

    @Id
    @Column(unique = true)
    private Long Id;
    private String title;
    private Integer downloads;

    private String authorName;
    private Integer birthYear;
    private Integer deathYear;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    public BookModel(){

    }

    public BookModel(DataBooks Book) {
        this.Id = Long.valueOf(Book.Id());
        this.title = Book.Title();
        this.downloads = Book.Downloads();

        if (!Book.Authors().isEmpty()) {
            DataAuthors authors = Book.Authors().get(0);
            this.authorName = authors.Name();
            this.birthYear = authors.BirthYear();
            this.deathYear = authors.DeathYear();
        } else {
            this.authorName = "Desconocido";
            this.birthYear = 0;
            this.deathYear = 0;
        }

        if (!Book.Language().isEmpty()) {
            this.languages = Book.Language();
        } else {
            this.languages = Arrays.asList("Desconocido");
        }
    }

    public List<String> getLanguages() {
        return languages;
    }
    public String getBook(){

        String message = String.format("""
                ------------------------------------------------
                Datos Del Libro
                ------------------------------------------------
                Id = %d
                Nombre = %s.
                Autor = %s.
                Año de Nacimiento = %d
                Año de Muerte = %d
                Idiomas = %s
                Descargas = %d
                ------------------------------------------------
                """,this.Id, this.title, this.authorName, this.birthYear, this.deathYear, this.languages.toString(), this.downloads);

        return message;
    }
    public Long getId(){
        return this.Id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getAuthorName(){
        return  this.authorName;
    }
    public Integer getBirthYear() {
        return birthYear;
    }
    public Integer getDeathYear() {
        return deathYear;
    }
}
