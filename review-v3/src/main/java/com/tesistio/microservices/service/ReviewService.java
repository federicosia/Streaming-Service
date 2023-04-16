package com.tesistio.microservices.service;

import com.tesistio.microservices.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review saveReview(Review review);
    void addLikeToReview(long id);
    void removeLikeToReview(long id);
    Optional<Review> findById(long id);
    List<Review> findByRating(int starts);
    List<Review> findByRatingBetween(int start, int end);
    Review[] findByDetailsId(long id);
}
