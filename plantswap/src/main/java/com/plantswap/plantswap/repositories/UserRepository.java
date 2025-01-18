package com.plantswap.plantswap.repositories;

import com.plantswap.plantswap.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
