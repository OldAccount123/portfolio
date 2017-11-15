package com.portfolio.portfolio.admin;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends ReactiveCrudRepository<Coin, ObjectId>{

}
