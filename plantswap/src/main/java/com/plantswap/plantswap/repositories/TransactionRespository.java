package com.plantswap.plantswap.repositories;

import com.plantswap.plantswap.models.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRespository extends MongoRepository<Transactions, String>{
}
