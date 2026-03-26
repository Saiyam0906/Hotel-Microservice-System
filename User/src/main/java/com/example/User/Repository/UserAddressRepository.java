package com.example.User.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long>{

	boolean existsByUserId(Long userId);
	
	Optional<UserAddress> findByUserId(Long userId);

}
