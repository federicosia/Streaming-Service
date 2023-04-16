package com.tesistio.microservices.services;

import com.tesistio.microservices.model.Film;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FilmService {
    Mono<Film> retrieveFilmByName(String text);
    Flux<Film> retrieveFilmsByName(String text);
    Mono<Film> createFilm(Film film);
    Mono<Long> getId();
}
