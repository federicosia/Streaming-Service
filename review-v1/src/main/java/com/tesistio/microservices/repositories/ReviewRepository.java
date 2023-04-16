package com.tesistio.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tesistio.microservices.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review[] findByDetailsId(long id);
}
