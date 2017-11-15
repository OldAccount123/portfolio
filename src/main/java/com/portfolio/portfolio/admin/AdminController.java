package com.portfolio.portfolio.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RequestMapping("/admin")
@RestController
public class AdminController {
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	@Autowired
	private CoinRepository coinRepository;	
	
	@GetMapping("/addExchange")
	Mono<Exchange> addExchange(@RequestBody Exchange exchange){
		return exchangeRepository.save(exchange);
	}
	
	@GetMapping("/addCoin")
	Mono<Coin> addCoin(@RequestBody Coin coin){
		return coinRepository.save(coin);
	}
}
