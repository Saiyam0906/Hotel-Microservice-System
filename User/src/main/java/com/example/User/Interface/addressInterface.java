package com.example.User.Interface;

import com.example.User.Request.UserAddressRequestDto;
import com.example.User.Response.UserAddressResponseDto;
import com.example.User.Update.UserAddressUpdateDto;

public interface addressInterface {
	
	UserAddressResponseDto createAddress(Long userId, UserAddressRequestDto requestDto);

    UserAddressResponseDto getAddressByUserId(Long userId);

    UserAddressResponseDto updateAddress(Long userId, UserAddressUpdateDto updateDto);

    void deleteAddress(Long userId);
}
