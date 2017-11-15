package com.portfolio.portfolio.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.portfolio.user.User;
import com.portfolio.portfolio.user.UserRepository;

import reactor.core.publisher.Mono;

@RestController
public class PortfolioController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/getUserPortfolio/{id}")
	Mono<String> getUserPortfolio(@RequestParam("id") String login) {
		return Mono.just("foo");
	}
}
