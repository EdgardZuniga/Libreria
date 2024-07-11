package com.AluraChallenge.Library.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Autores")
public class AuthorModel {
    @Id
    Long id;
    Integer birthYear;
    Integer deathYear;

    @Column(unique = true)
    String name;

    public AuthorModel(){}
    public AuthorModel(Long id, Integer birthYear, Integer deathYear, String name) {
        this.id = id;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getAuthor(){

        String message = String.format("""
                ------------------------------------------------
                Datos Del Autor
                ------------------------------------------------
                Id = %d
                Nombre = %s.
                Año de Nacimiento = %d
                Año de Muerte = %d
                ------------------------------------------------
                """,this.id, this.name, this.birthYear, this.deathYear);

        return message;
    }
    public Integer getBirthYear() {
        return birthYear;
    }
    public Integer getDeathYear() {
        return deathYear;
    }
    public Long getId() {
        return this.id;
    }
}
