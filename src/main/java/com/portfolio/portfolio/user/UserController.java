package com.portfolio.portfolio.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/**
	 * Form post from signup
	 * @param user: user to be registered
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	Mono<User> userSignUp(@RequestBody User user, ServerHttpRequest request) {
		User newUser = userService.findByEmail(user.getEmail()).block();
		if (newUser != null) {
			System.out.println("User already reggistered");
			return null;
		}
		// disable user until he confirms e-mail address
		user.setEnabled(false);
		user.setConfirmationToken(UUID.randomUUID().toString());

		SimpleMailMessage registrationEmail = getRegistrationMail(user, request);
		emailService.sendEmail(registrationEmail);
		return userService.save(user);
	}

	@RequestMapping(value = "/signup/confirm", method = RequestMethod.GET)
	Mono<User> processConfirmationForm(@RequestParam("token") String token) {
		User user = userService.findByConfirmationToken(token).block();
		if (user == null) {
			return Mono.just(user);
		} else {
			user = userService.findByConfirmationToken(token).block();
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setEnabled(true);
			return userService.save(user);
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	Mono<String> userLogin(@RequestBody User user){
		User foundUser = userService.findByUsername(user.getUsername()).block();
		if(foundUser == null) {
			return Mono.just("Not found");
		}
		if(successfulLogin(user, foundUser)) {
			return Mono.just("Found");
		}
		return Mono.just("Error:");
	}

	/**
	 * Compare users after login page (password, username)
	 * @param user mapped object from login form
	 * @param foundUser user found in db via username
	 * @return true if successful login, else false
	 */
	private boolean successfulLogin(User user, User foundUser) {
		if(bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword()) && user.getUsername().equals(foundUser.getUsername())){
			System.out.println(bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword()));
			return true;
		}
		return false;
	}

	private SimpleMailMessage getRegistrationMail(User user, ServerHttpRequest request) {
		SimpleMailMessage registrationMail = new SimpleMailMessage();
		registrationMail.setTo(user.getEmail());
		registrationMail.setSubject("Registration confirmation");
		registrationMail.setText("To confirm your e-mail address, please click the link below " + request.getURI()
				+ "/confirm?token=" + user.getConfirmationToken());
		registrationMail.setFrom("matej.gazda@6wizards.com");
		return registrationMail;
	}

}