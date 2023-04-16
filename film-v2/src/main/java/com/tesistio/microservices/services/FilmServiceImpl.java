package com.tesistio.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesistio.microservices.model.Film;
import com.tesistio.microservices.repositories.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService{
    @Autowired
    private FilmRepository repository;

    @Override
    public Film retrieveFilmByName(String text) {
        return repository.findByName(text);
    }

    @Override
    public List<Film> retrieveFilmsByName(String text) {
        return repository.findByNameContaining(text);
    }

    @Override
    public void createFilm(Film film) {
        //flush because we want the id of the film after the store happened
        repository.saveAndFlush(film);
    }
}
