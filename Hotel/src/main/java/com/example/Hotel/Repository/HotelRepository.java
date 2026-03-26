package com.example.Hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Hotel.Entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{
	boolean existsByNameAndLocation(String name, String location);
}
