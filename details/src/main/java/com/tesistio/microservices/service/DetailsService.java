package com.tesistio.microservices.service;

import com.tesistio.microservices.model.Details;

import java.util.Optional;

public interface DetailsService {
    Optional<Details> findById(long id);
    Details findByAuthorsContaining(String authors);
    Details findByFilm_id(long id);
}
