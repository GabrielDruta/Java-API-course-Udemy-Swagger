package com.appsdeveloperblog.app.ws.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.appsdeveloperblog.app.ws.exception.UserServiceException;
import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.response.AddressesRest;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	Utils utils;

	String userId = "loluserid";

	UserEntity userEntity = new UserEntity();

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userEntity = new UserEntity();

		userEntity.setId(1L);
		userEntity.setFirstName("gabi");
		userEntity.setLastName("druta");
		userEntity.setUserId(userId);
		userEntity.setEmail("testemail");
		userEntity.setAddresses(getAddressesEntity());
	}

	@Test
	final void testGetUser() {

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = userService.getUser("lol");

		assertNotNull(userDto);
		assertEquals("gabi", userDto.getFirstName());
	}

	@Test
	final void testGetUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(RuntimeException.class, () -> {
			userService.getUser("dsds");
		});

	}

	@Test
	final void testCreateUser() {
		
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		
		when(utils.generateAddressId(anyInt())).thenReturn("fdfdfddfd");	
		when(utils.generateUserId(anyInt())).thenReturn("userId");
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
				
		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressesDto());
		userDto.setEmail("testemail");
		userDto.setFirstName("gabi");
		userDto.setLastName("druta");
		userDto.setPassword("parola");
		
		
		UserDto storedUserDetails=userService.createUser(userDto);
	
		assertNotNull(storedUserDetails);
		assertEquals(userDto.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userDto.getLastName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());
		assertEquals(userEntity.getAddresses().size(), storedUserDetails.getAddresses().size());
		verify(userRepository, times(1)).save(any(UserEntity.class));
		verify(utils, times(storedUserDetails.getAddresses().size())).generateAddressId(30);
		
	}
	
	

	private List<AddressDTO> getAddressesDto() {
		AddressDTO addressDto = new AddressDTO();
		addressDto.setType("shipping");
		addressDto.setCity("gl");
		addressDto.setCountry("ro");
		addressDto.setPostalCode("807310");
		addressDto.setStreetName("umbraresti");

		AddressDTO billindAddressDto = new AddressDTO();
		addressDto.setType("billing");
		addressDto.setCity("gl");
		addressDto.setCountry("ro");
		addressDto.setPostalCode("807310");
		addressDto.setStreetName("umbraresti");

		List<AddressDTO> addresses = new ArrayList<>();
		addresses.add(addressDto);
		addresses.add(billindAddressDto);
		return addresses;
	}

	final private List<AddressEntity> getAddressesEntity(){
		
		List<AddressDTO> addressesDto=getAddressesDto();
		
		Type listType = new TypeToken<List<AddressesRest>>() {}.getType();
		return new ModelMapper().map(addressesDto, listType);
	}
	
	
	
	
	
	

	@Test final void testCreateUser_UserServiceException() {

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressesDto());
		userDto.setEmail("testemail");
		userDto.setFirstName("gabi");
		userDto.setLastName("druta");
		userDto.setPassword("parola");
		
		assertThrows(UserServiceException.class, () -> {
			userService.createUser(userDto);
		});

	}

}
