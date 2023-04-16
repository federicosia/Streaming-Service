package com.tesistio.microservices.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    @Size(min = 4, message = "The description should be atleast 4 characters...")
    private String content;

    @Size(min = 1, max = 5, message = "The rating should be a value between 1 and 5")
    private int rating = 1;

    private int likes = 0;

    private long detailsId;

    public Review() {
    }

    public Review(long id, String title, String description, int rating, int likes, int detailsId) {
        this.id = id;
        this.title = title;
        this.content = description;
        this.rating = rating;
        this.likes = likes;
        this.detailsId = detailsId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", likes=" + likes +
                ", detailsId=" + detailsId +
                '}';
    }
}
