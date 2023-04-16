package com.tesistio.microservices.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.tesistio.microservices.utils.Nationality;

@Entity
@Table(name = "utente")
public class User {
    @Size(min=2, message = "Name should have atleast 2 characters...")
    private String name;
    @Size(min=2, message = "Surname should have atleast 2 characters...")
    private String surname;
    @Email
    @NotBlank
    @Id
    private String email;
    @Past(message = "Birthdate should be in the past...")
    private LocalDate birthdate;
    private Nationality nationality;

    public User() {}

    public User(String name, String surname, String email, LocalDate birthdate, Nationality nationality) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthdate = birthdate;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
