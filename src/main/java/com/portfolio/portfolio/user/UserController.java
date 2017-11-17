package com.portfolio.portfolio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private EmailService emailService;

	@Autowired
	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
			EmailService emailService) {
		super();
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.emailService = emailService;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	Mono<User> userSignUp(@RequestBody User user, ServerHttpRequest request) {
		User newUser = userService.findByEmail(user.getEmail()).block();
		if(newUser != null) {
			System.out.println("User already reggistered");
			return null;
		}
		// disable user until he confirms e-mail address
		user.setEnabled(false);
		SimpleMailMessage registrationEmail = getRegistrationMail(user, request);
		return userService.save(user);
	}
	
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	void processConfirmationStep() {
		
	}
	
	
	private SimpleMailMessage getRegistrationMail(User user, ServerHttpRequest request) {
		SimpleMailMessage registrationMail = new SimpleMailMessage();
		registrationMail.setTo(user.getEmail());
		registrationMail.setSubject("Registration confirmation");
		registrationMail.setText("To confirm your e-mail address, please click the link below " + request.getURI() + "/confirm?token=" + user.getConfirmationToken());
		registrationMail.setFrom("portfoliotracker@mato.sk");
		return registrationMail;
	}

}