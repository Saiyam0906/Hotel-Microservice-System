package com.example.User.MapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.User.Entity.UserAddress;
import com.example.User.Request.UserAddressRequestDto;
import com.example.User.Response.UserAddressResponseDto;
import com.example.User.Update.UserAddressUpdateDto;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
	
	UserAddress toEntity(UserAddressRequestDto requestDto);
	
	UserAddressResponseDto toResponseDto(UserAddress address);

	void updateEntityFromDto(UserAddressUpdateDto updateDto, @MappingTarget UserAddress address);

}

