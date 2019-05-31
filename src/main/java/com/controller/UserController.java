package com.controller;

import java.util.List;


import javax.websocket.server.PathParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserManager;
import com.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")

public class UserController {

	@Autowired  
	UserService UserService;
	/*
	@RequestMapping(value="/findByLogin",method=RequestMethod.GET)
	public UserManager findByLogin(@PathParam("login") String login)
	{
		return UserService.findByLogin(login);
	}*/
	
	@PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_ADMIN')")
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<UserManager> findALL()
	{
		System.out.println("hello");
		System.out.println("margot");
		return UserService.findALL();
	}

	
}
