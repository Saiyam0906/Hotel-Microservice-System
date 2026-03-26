package com.example.User.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.User.Entity.User;
import com.example.User.Entity.UserAddress;
import com.example.User.Exception.ResourceAlreadyExistsException;
import com.example.User.Exception.ResourseNotFound;
import com.example.User.Interface.addressInterface;
import com.example.User.MapStruct.AddressMapper;
import com.example.User.Repository.UserAddressRepository;
import com.example.User.Repository.UserRepository;
import com.example.User.Request.UserAddressRequestDto;
import com.example.User.Response.UserAddressResponseDto;
import com.example.User.Update.UserAddressUpdateDto;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class addressService implements addressInterface{
	
	
	private final UserRepository userRepository;
	private final UserAddressRepository addressRepository;
	private final AddressMapper addressMapper;

	@Override
    @Transactional
    public UserAddressResponseDto createAddress(Long userId, UserAddressRequestDto requestDto) {
        log.info("Creating address for user id: {}", userId);
        
        if (addressRepository.existsByUserId(userId)) {
            throw new ResourceAlreadyExistsException(
                "Address already exists for user id: " + userId
            );
        }

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourseNotFound(
                "User not found with id: " + userId
            ));

       

        UserAddress address = addressMapper.toEntity(requestDto);
        address.setUser(user);
        UserAddress saved = addressRepository.save(address);

        log.info("Address created successfully with id: {}", saved.getId());
        return addressMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAddressResponseDto getAddressByUserId(Long userId) {
        log.info("Fetching address for user id: {}", userId);

        UserAddress address = addressRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourseNotFound(
                "Address not found for user id: " + userId
            ));

        return addressMapper.toResponseDto(address);
    }

    @Override
    @Transactional
    public UserAddressResponseDto updateAddress(Long userId, UserAddressUpdateDto updateDto) {
        log.info("Updating address for user id: {}", userId);
        
        if (!userRepository.existsById(userId)) {
            throw new ResourseNotFound("User not found with id: " + userId);
        }

        UserAddress address = addressRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourseNotFound(
                "Address not found for user id: " + userId
            ));

        addressMapper.updateEntityFromDto(updateDto, address);
        UserAddress saved = addressRepository.save(address);

        log.info("Address updated successfully for user id: {}", userId);
        return addressMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void deleteAddress(Long userId) {
        log.info("Deleting address for user id: {}", userId);
        
        if (!userRepository.existsById(userId)) {
            throw new ResourseNotFound("User not found with id: " + userId);
        }

        UserAddress address = addressRepository.findByUserId(userId)
            .orElseThrow(() -> new ResourseNotFound(
                "Address not found for user id: " + userId
            ));

        addressRepository.delete(address);
        log.info("Address deleted successfully for user id: {}", userId);
    }

}
