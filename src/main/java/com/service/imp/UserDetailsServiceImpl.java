package com.service.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.UserManager;
import com.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserManager usermanger = userService.findByLogin(username);
		if (usermanger == null) throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		for (int i = 0; i < usermanger.getRoles().size(); i++) {
			authorities.add(new SimpleGrantedAuthority(usermanger.getRoles().get(i).getNomRole()));
		}
		return (UserDetails) new User(usermanger.getLogin(), usermanger.getPassword(), authorities);

	}

}
