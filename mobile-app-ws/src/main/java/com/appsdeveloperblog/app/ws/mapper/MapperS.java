package com.appsdeveloperblog.app.ws.mapper;

public interface MapperS<D,E>  {

	D entityToDto(E ent);
	 E dtoToEntity(D dto);
	
}
