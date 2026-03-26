package com.example.Rating.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Rating.Entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	Optional<Rating> findById(Long ratingId);

	void deleteById(Long ratingId);

	List<Rating> findByUserId(Long userId);
	
	
    boolean existsByUserIdAndHotelId(Long userId, Long hotelId); // ← add this

}
