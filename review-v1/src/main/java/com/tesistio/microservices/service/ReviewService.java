package com.tesistio.microservices.service;

import java.util.Optional;

import com.tesistio.microservices.model.Review;

public interface ReviewService {
    Review saveReview(Review review);
    Optional<Review> findById(long id);
    Review[] findByDetails_id(long id);
}
