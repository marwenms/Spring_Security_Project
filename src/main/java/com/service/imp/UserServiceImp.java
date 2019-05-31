package com.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserRepository;
import com.entity.UserManager;
import com.service.UserService;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserManager findByLogin(String login) {
		// TODO Auto-generated method stub
		return userRepository.findByLogin(login);
	}

	@Override
	public List<UserManager> findALL() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}
