package com.tesistio.microservices.service;

import com.tesistio.microservices.repositories.DetailsRepository;
import com.tesistio.microservices.model.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailsServiceImpl implements DetailsService {
    @Autowired
    private DetailsRepository repository;

    @Override
    public Optional<Details> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Details findByAuthorsContaining(String authors) {
        return repository.findByAuthorsContaining(authors);
    }

    @Override
    public Details findByFilm_id(long id) {
        return repository.findByFilmId(id);
    }

    public void save(Details details) {
        repository.save(details);
    }
}
