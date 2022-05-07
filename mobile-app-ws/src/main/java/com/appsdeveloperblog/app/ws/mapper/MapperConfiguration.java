package com.appsdeveloperblog.app.ws.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
		componentModel="spring",
		injectionStrategy=InjectionStrategy.CONSTRUCTOR,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
		)
public class MapperConfiguration {

	
	
}
