package com.tesistio.microservices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tesistio.microservices.model.Review;
import com.tesistio.microservices.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository repository;

    @Override
    public Review saveReview(Review review) {
        return repository.save(review);
    }

    @Override
    public Optional<Review> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Review[] findByRating(int stars) {
        return repository.findByRating(stars);
    }

    @Override
    public Review[] findByRatingBetween(int start, int end) {
        return repository.findByRatingBetween(start, end);
    }

    @Override
    public Review[] findByDetailsId(long id) {
        return repository.findByDetailsId(id);
    }
}
