package com.tesistio.microservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tesistio.microservices.model.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByName(String text);
    List<Film> findByNameContaining(String text);
}
