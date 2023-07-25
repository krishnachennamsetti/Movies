package com.assignment.movies.repository;

import com.assignment.movies.entity.UsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<UsersEntity, String> {

    Optional<UsersEntity> findByUsername(String username);

    Optional<UsersEntity> findByUsernameAndCredential(String username, String credential);

}
