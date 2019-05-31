package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.UserManager;

public interface UserRepository extends JpaRepository<UserManager, Long> {
	
public UserManager findByLogin(String login);
}
