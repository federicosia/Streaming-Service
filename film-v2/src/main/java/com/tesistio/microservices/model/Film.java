package com.tesistio.microservices.model;

import com.tesistio.microservices.utils.Genre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(name = "film_name")
    private String name;
    @NotNull
    private LocalDate issue;
    @NotNull
    private long duration;
    @NotNull
    private Genre genre;

    public Film() {
    }

    public Film(long id, String name, LocalDate release, long duration, Genre genre) {
        this.id = id;
        this.name = name;
        this.issue = release;
        this.duration = duration;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIssue() {
        return issue;
    }

    public void setIssue(LocalDate issue) {
        this.issue = issue;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release=" + issue +
                ", length=" + duration +
                ", genre=" + genre +
                '}';
    }
}
