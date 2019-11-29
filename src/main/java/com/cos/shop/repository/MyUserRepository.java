package com.cos.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.shop.model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer>{

	MyUser findByUsername(String username);
	
}
