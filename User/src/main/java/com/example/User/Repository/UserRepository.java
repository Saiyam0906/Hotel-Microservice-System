package com.example.User.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
