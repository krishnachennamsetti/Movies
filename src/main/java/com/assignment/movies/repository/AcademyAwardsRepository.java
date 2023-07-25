package com.assignment.movies.repository;

import com.assignment.movies.entity.AcademyAwardsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AcademyAwardsRepository extends MongoRepository<AcademyAwardsEntity, String> {

    @Query("{'nominee' : { $regex:?0, $options:'i'} }")
    Optional<AcademyAwardsEntity> findByNomineeAndCategoryIgnoreCase(String nominee, String category);

}
