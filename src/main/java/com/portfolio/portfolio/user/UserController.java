package com.portfolio.portfolio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	Mono<User> userSignUp(@RequestBody User user) {
		return repository.save(user);
	}
}
