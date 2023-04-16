package com.tesistio.microservices.model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import com.tesistio.microservices.utils.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collectionName = "film")
public class Film {
    @DocumentId
    private String documentId;
    private long id;
    @NotBlank
    private String name;
    private Timestamp issue;
    @NotNull
    private long duration;
    @NotNull
    private Genre genre;

    public Film() {
    }

    public Film(long id, String name, Timestamp release, long duration, Genre genre) {
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

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getIssue() {
        return issue;
    }

    public void setIssue(Timestamp issue) {
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
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", release=" + issue +
                ", length=" + duration +
                ", genre=" + genre +
                '}';
    }
}
