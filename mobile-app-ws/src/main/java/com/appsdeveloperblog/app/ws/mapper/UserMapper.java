package com.appsdeveloperblog.app.ws.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

@Mapper(config = MapperConfiguration.class, uses = {
AddressMapper.class
})
public interface UserMapper extends MapperS<UserDto, UserEntity> {

	//@Mapping(target = "addresses", ignore = true)
	@Override
	UserDto entityToDto(UserEntity ent);
	
}
