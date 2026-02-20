package com.example.User.Service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.User.Entity.Hotel;
import com.example.User.Entity.Rating;
import com.example.User.Entity.UserEntity;
import com.example.User.Exception.UserNotFoundException;
import com.example.User.FeingInterface.HotelInterface;
import com.example.User.FeingInterface.RatingService;
import com.example.User.Interface.UserInterface;
import com.example.User.Repository.UserRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserService implements UserInterface{

	private final UserRepository userRepository;
	
	private final RatingService ratingService;
	
	private final HotelInterface hotelInterface;

	@Override
	public UserEntity saveUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> getAlluser() {
		
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUser(Long userId) {
	
		 UserEntity user = userRepository.findById(userId)
		            .orElseThrow(() -> new UserNotFoundException("User not found"));

		    List<Rating> ratings = ratingService.getRatingsByUserId(userId);

		    ratings.forEach(rating->{
		    	Hotel hotel=hotelInterface.getHotelById(rating.getHotelId());
		    	rating.setHotel(hotel);
		    });
		    user.setRatings(ratings);
		    
		  
		    return user;
	}
	
	
}
