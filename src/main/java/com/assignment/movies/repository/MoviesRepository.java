package com.assignment.movies.repository;

import com.assignment.movies.entity.MoviesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends MongoRepository<MoviesEntity, String> {

  @Query("{'title' : { $regex:?0, $options:'i'} }")
  Optional<MoviesEntity> findByTitleIgnoreCase(String title);

  List<MoviesEntity> findTop10ByOrderByRatingDescBoxOfficeDesc();

}
