package com.portfolio.portfolio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service("userService")
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Mono<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Mono<User> save(User user) {
		return userRepository.save(user);
	}

	public Mono<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
