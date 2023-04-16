package com.tesistio.microservices.services;

import java.util.List;

import com.tesistio.microservices.model.Film;

public interface FilmService {
    Film retrieveFilmByName(String text);
    List<Film> retrieveFilmsByName(String text);
    void createFilm(Film film);
}
