package com.assignment.movies.mapper;

import com.assignment.movies.entity.MoviesEntity;
import com.assignment.movies.model.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MoviesMapper {

    List<Movie> mapMoviesEntityToMovies(List<MoviesEntity> movies);

    Movie mapMovieEntityToMovie(MoviesEntity entity);

}
