package com.example.Rating.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ratings")
@Data               // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Generates no-args constructor
@AllArgsConstructor // Generates all-args constructor
@Builder         
public class Rating {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID
    private Long ratingId;

    private Long userId;

    private Long hotelId;

    private int rating;

    private String feedback;
}
