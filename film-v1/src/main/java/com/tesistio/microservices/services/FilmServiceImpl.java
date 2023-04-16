package com.tesistio.microservices.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.Timestamp;
import com.tesistio.microservices.model.Film;
import com.tesistio.microservices.repositories.FilmRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FilmServiceImpl implements FilmService{
    @Autowired
    private FilmRepository repository;

    @Override
    public Mono<Film> retrieveFilmByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Flux<Film> retrieveFilmsByName(String text) {
        return repository.findByNameGreaterThanEqual(text);
    }

    @Override
    public Mono<Film> createFilm(Film film) {
        film.setIssue(Timestamp.of(new Date()));
        return repository.save(film);
    }

    @Override
    public Mono<Long> getId() {
        return repository.count();
    }
}
