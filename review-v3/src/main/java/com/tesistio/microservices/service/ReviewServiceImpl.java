package com.tesistio.microservices.service;

import com.tesistio.microservices.model.Review;
import com.tesistio.microservices.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository repository;

    @Override
    public Review saveReview(Review review) {
        return repository.save(review);
    }

    @Override
    public void addLikeToReview(long id) {
        repository.addLikeToReview(id);
    }

    @Override
    public void removeLikeToReview(long id) {
        repository.removeLikeToReview(id);
    }

    @Override
    public Optional<Review> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Review> findByRating(int stars) {
        return repository.findByRating(stars);
    }

    @Override
    public List<Review> findByRatingBetween(int start, int end) {
        return repository.findByRatingBetween(start, end);
    }

    @Override
    public Review[] findByDetailsId(long id) {
        return repository.findByDetailsId(id);
    }
}
