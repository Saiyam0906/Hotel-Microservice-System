package com.example.User.Entity;

import java.time.LocalDate;

import jakarta.persistence.Index;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import com.example.User.enums.Gender;
import com.example.User.enums.UserStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
	    name = "users",
	    uniqueConstraints = {
	        @UniqueConstraint(name = "uk_users_email", columnNames = "email")  
	    },
	    indexes = {
	        @Index(name = "idx_users_email", columnList = "email"),             
	        @Index(name = "idx_users_status", columnList = "status")
	    }
	)
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;  
    
    private LocalDate dateOfBirth;

    @Column(length = 15)
    private String phone;

    private String profilePhotoKey;  
    

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status=UserStatus.ACTIVE;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private UserAddress address;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
