package com.example.User.FeingInterface;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.User.Entity.Hotel;

@FeignClient(name="HOTEL")
public interface HotelInterface {

	@GetMapping("/hotels/{Id}")
	Hotel getHotelById(@PathVariable("Id") Long id);
}
