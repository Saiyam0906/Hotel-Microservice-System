package com.example.User.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Index;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	    name = "user_addresses",
	    uniqueConstraints = {
	        @UniqueConstraint(name = "uk_user_addresses_user_id", columnNames = "user_id") // one address per user
	    },
	    indexes = {
	        @Index(name = "idx_user_addresses_user_id", columnList = "user_id"),  // always queried by user_id
	        @Index(name = "idx_user_addresses_city", columnList = "city"),         // useful for hotel location matching later
	        @Index(name = "idx_user_addresses_pincode", columnList = "pincode")    // delivery/service area checks
	    }
	)
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserAddress {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false, unique = true)
	    private User user;

	    private String street;
	    private String city;
	    private String state;
	    private String country;
	    private String pincode;
	    
	    @CreatedDate
	    @Column(updatable = false)
	    private LocalDateTime createdAt;

	    @LastModifiedDate
	    private LocalDateTime updatedAt;

}
