package com.service;

import java.util.List;

import com.entity.UserManager;

public interface UserService {
	
	public UserManager findByLogin(String login);
	
	public List<UserManager> findALL();

}
