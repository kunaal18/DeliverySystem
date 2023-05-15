package com.te.deliverysystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.deliverysystem.entity.UserDetails;

public interface UserDao extends JpaRepository<UserDetails, Integer>{

	UserDetails findByUserId(String userId);
	
	UserDetails findByUserName(String userName);
	
	UserDetails findByEmailId(String emailId);
}
