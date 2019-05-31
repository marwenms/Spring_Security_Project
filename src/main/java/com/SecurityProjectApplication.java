package com;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.bouncycastle.crypto.tls.HashAlgorithm;

@SpringBootApplication
public class SecurityProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityProjectApplication.class, args);
	}
	
	@Bean
	@Primary
	public BCryptPasswordEncoder getpce()
	{
		return new BCryptPasswordEncoder();
	}
	
	public static String hash(String password,int row) {
        return BCrypt.hashpw(password, BCrypt.gensalt(row));
    }

}

