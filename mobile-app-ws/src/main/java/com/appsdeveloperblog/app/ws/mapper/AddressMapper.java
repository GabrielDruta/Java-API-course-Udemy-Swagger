package com.appsdeveloperblog.app.ws.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;

@Mapper(config=MapperConfiguration.class)
public interface AddressMapper extends MapperS<AddressDTO, AddressEntity> {
	@Mapping(target = "userDetails", ignore = true)
	@Override
	AddressDTO entityToDto(AddressEntity ent);
	
}
