package com.security;

import java.io.IOException;

import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.entity.UserManager;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authentificationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserManager  emp = null;
		
		try {
			emp = new ObjectMapper().readValue(request.getInputStream(), UserManager.class);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
		
		return authentificationManager
				.authenticate(new UsernamePasswordAuthenticationToken(emp.getLogin(), emp.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User springUser =(User) authResult.getPrincipal();
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		requestURI = requestURI.substring(contextPath.length());
		setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler(requestURI)) ;
	
		String jwt=Jwts.builder().setSubject(
				springUser.getUsername()).setExpiration
				(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);
	
	}

	public JWTAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authentificationManager = authenticationManager;
	}

}
