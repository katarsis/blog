package com.kapetingi.blog;

import com.kapetingi.blog.entities.Role;
import com.kapetingi.blog.entities.User;
import com.kapetingi.blog.repositories.UserRepository;
import com.kapetingi.blog.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;


@SpringBootApplication
public class BlogApplication {

	private AuthenticationManagerBuilder builder;
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception {
		this.builder = builder;
		this.userRepository = userRepository;
		if(userRepository.count() == 0) {
			userRepository.save(new User("user", "$2a$10$8bpdlCMQemCYNtXEJxeZu.ZK/W5At2iCtvAVi9vb31tYLO80Yc2eC", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
			userRepository.save(new User("user1", "$2a$10$8bpdlCMQemCYNtXEJxeZu.ZK/W5At2iCtvAVi9vb31tYLO80Yc2eC", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
		}
		builder.userDetailsService(customerDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}


}
