package com.tesistio.microservices.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.tesistio.microservices.model.Film;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FilmRepository extends FirestoreReactiveRepository<Film> {
    Mono<Film> findByName(String name);
    Flux<Film> findByNameGreaterThanEqual(String name);
}
