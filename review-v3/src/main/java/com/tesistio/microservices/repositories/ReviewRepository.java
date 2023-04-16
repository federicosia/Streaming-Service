package com.tesistio.microservices.repositories;

import com.tesistio.microservices.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRating(int stars);
    List<Review> findByRatingBetween(int start, int end);
    @Modifying
    @Query("UPDATE Review r set r.likes = r.likes + 1 WHERE r.id = :id")
    void addLikeToReview(long id);
    @Modifying
    @Query("UPDATE Review r set r.likes = r.likes - 1 WHERE r.id = :id")
    void removeLikeToReview(long id);
    Review[] findByDetailsId(long id);
}
