package com.appsdeveloperblog.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;


@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);
		
	@Query(value="select * from Users u where u.email_verification_status='false'",
			countQuery="select count(*) from Users u where u.EMAIL_VERIFICATION_STATUS='true'",
			nativeQuery=true)
	Page<UserEntity> findAllUsersByEmailStatus(Pageable pageableRequest);
	
	@Query(value="select * from Users u where u.first_name=?1", nativeQuery=true)
	List<UserEntity> findUsersByFirstName(String name);
	
	@Query(value="select * from Users u where u.last_name = :lastName", nativeQuery=true)
	List<UserEntity> findUsersByLastName(@Param("lastName")String name);
	
	
	@Query(value="select * from Users u where u.first_name LIKE %:keyword% or u.last_name LIKE %:keyword%", 
			nativeQuery=true)
	List<UserEntity> findUsersByKeyword(@Param("keyword")String keyword);
	
	@Query(value="select u.first_name, u.last_name from Users u where u.first_name LIKE %:keyword% or u.last_name LIKE %:keyword%", 
			nativeQuery=true)
	List<Object[]> findUserFirstNameAndLastNameByKeyword(@Param("keyword")String keyword);
	
	
	@Query("select user from UserEntity user where user.userId =:userId ")
	UserEntity findUserEntityByUserId(@Param("userId")String userId);
	 	
	
}