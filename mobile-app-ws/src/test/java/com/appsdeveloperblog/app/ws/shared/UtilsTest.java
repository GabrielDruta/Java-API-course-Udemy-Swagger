package com.appsdeveloperblog.app.ws.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
class UtilsTest {

	@Autowired
	Utils utils;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void testGenerateUserId() {
	
		String userId = new Utils().generateUserId(30);
		String userId2 = new Utils().generateUserId(30);
		
		assertNotNull(userId);assertNotNull(userId2);
		assertTrue(userId.length()==30); 
		assertTrue(!userId.equalsIgnoreCase(userId2));
	}

	@Test
	@Disabled
	void testGenerateAddressId() {
		fail("Not yet implemented");
	}

}
