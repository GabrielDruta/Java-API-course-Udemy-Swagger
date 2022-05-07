package com.appsdeveloperblog.app.ws.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.appsdeveloperblog.app.ws.service.impl.UserServiceImpl;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDTO;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;

class UserControllerTest {

	@Mock
	UserServiceImpl userService;
 
	@InjectMocks
	UserController userController;
	
	UserDto userDto;
	final String USER_ID="userIdBRO";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations .initMocks(this);
		
		userDto=new UserDto();
		userDto.setAddresses(getAddressesDto());
		userDto.setEmail("testemail");
		userDto.setFirstName("gabi");
		userDto.setLastName("druta");
		userDto.setPassword("parola");
		userDto.setUserId(USER_ID);
	}

	@Test
	void testGetUser() {
		when(userService.getUserByUserId(anyString())).thenReturn(userDto);
		
		UserRest userRest=userController.getUser(USER_ID);
		
		assertNotNull(userRest);
		assertEquals(userDto.getUserId(), userRest.getUserId());
		assertEquals(userDto.getFirstName(), userRest.getFirstName());
		assertEquals(userDto.getLastName(),userRest.getLastName());
		assertTrue(userDto.getAddresses().size()==userRest.getAddresses().size());
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
	
	

}
