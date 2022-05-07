 package com.appsdeveloperblog.app.ws.io.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

	
	@Autowired
	UserRepository userRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

//	@Test
//	void testGetVerifiedUsers() {
//		Pageable pageableRequest=PageRequest.of(0, 2);
//		
//		Page<UserEntity> pages= userRepository.findAllUsersByEmailStatus(pageableRequest);
//		assertNotNull(pages);
//		
//		List<UserEntity> userEntities=pages.getContent();
//		assertNotNull(userEntities);
//		assertTrue(userEntities.size()>=1);
//		assertTrue(userEntities.size()>=2);
//		//assertTrue(userEntities.size()>=3);
//		//assertTrue(userEntities.size()>=4);
//		
//	}
//	
//	@Test
//	final void testFindUsersByFirstName() {
//		
//		String firstName="Gabi";
//		List<UserEntity> userEntities = userRepository.findUsersByFirstName(firstName);
//		
//		assertNotNull(userEntities);
//		assertTrue(userEntities.size()>=1);
//		assertTrue(userEntities.size()>=2);
//		assertTrue(userEntities.size()>=5);
//		assertTrue(userEntities.size()>=7);
//		
//		UserEntity user=userEntities.get(0);
//		assertTrue(user.getFirstName().equals(firstName));
//	}
//	
//	@Test
//	final void testFindUsersByLastName() {
//		String lastName="Kargopolov";
//		List<UserEntity> userEntities = userRepository.findUsersByLastName(lastName);
//		
//		assertNotNull(userEntities);
//		assertTrue(userEntities.size()>=1);
//		//assertTrue(userEntities.size()>=2);
//		//assertTrue(userEntities.size()>=3);
//		//assertTrue(userEntities.size()>=4);
//		
//		UserEntity user=userEntities.get(0);
	
//		assertTrue(user.getLastName().equals(lastName));
//		
//	}
//	
	@Test
	final void testFindUsersByKeyword() {
		String keyword="Gabi";
		List<UserEntity> userEntities = userRepository.findUsersByKeyword(keyword);
		
		assertNotNull(userEntities);
		assertTrue(userEntities.size()>=1);
		assertTrue(userEntities.size()>=2);
		assertTrue(userEntities.size()>=7);
		assertTrue(userEntities.size()>=4);
		
		UserEntity user=userEntities.get(0);
		System.out.println(user.getLastName());
		
		for(UserEntity user1 : userEntities) {
			System.out.println(user1.getFirstName());
			System.out.println(user1.getLastName());
		}
		
		assertTrue(user.getLastName().contains(keyword)||
				user.getFirstName().contains(keyword));
		
		
	}
	
	@Test
	final void testFindUserFirstNameAndLastNameByKeyword () {
		String keyword="Gabi";
		List<Object[]> userEntities = userRepository.findUserFirstNameAndLastNameByKeyword(keyword);
		
		assertNotNull(userEntities);
		assertTrue(userEntities.size()>=1);
		assertTrue(userEntities.size()>=2);
		assertTrue(userEntities.size()>=7);
		assertTrue(userEntities.size()>=4);
		
		Object[] user=userEntities.get(0);

		String userFirstName=String.valueOf(user[0]);
		String userLastName=String.valueOf(user[1]);
	
		assertNotNull(userFirstName);
		assertNotNull(userLastName);
		
		System.out.println("First name: "+ userFirstName);
		System.out.println("Last name: "+ userLastName);
		
		assertTrue(user.length==2);
		
	}
	
}
