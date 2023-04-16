package com.tesistio.microservices.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String authors;

    @Size(min = 5, message = "The description should be atleast 5 characters...")
    private String content;

    @NotNull
    private long filmId;

    public Details() {
    }

    public Details(long id, String authors, String description, long filmId) {
        this.id = id;
        this.authors = authors;
        this.content = description;
        this.filmId = filmId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", authors='" + authors + '\'' +
                ", content='" + content + '\'' +
                ", filmId=" + filmId +
                '}';
    }
}

