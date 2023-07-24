package com.assignment.movies.service;

import com.assignment.movies.client.MoviesClient;
import com.assignment.movies.constants.MoviesConstants;
import com.assignment.movies.entity.AcademyAwardsEntity;
import com.assignment.movies.entity.MoviesEntity;
import com.assignment.movies.enums.MoviesErrorEnum;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.mapper.MoviesMapper;
import com.assignment.movies.model.BestPictureResponse;
import com.assignment.movies.model.MovieOmdbResponse;
import com.assignment.movies.model.Movies;
import com.assignment.movies.repository.AcademyAwardsRepository;
import com.assignment.movies.repository.MoviesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class MoviesServiceImpl implements MoviesService {

    private final MoviesClient client;

    private final AcademyAwardsRepository academyAwardsRepository;

    private final MoviesRepository moviesRepository;

    private final MoviesMapper moviesMapper;

    @Override
    public BestPictureResponse checkOscarBestPicture(String title) throws MoviesException {
        BestPictureResponse bestPicture = null;
        MovieOmdbResponse response =  client.fetchMovieByTitle(title);
        if(Objects.nonNull(response) && title.equalsIgnoreCase(response.getTitle())){
            bestPicture = new BestPictureResponse();
          Optional<AcademyAwardsEntity> academyAwards = academyAwardsRepository.findByNomineeAndCategoryIgnoreCase(title, MoviesConstants.BEST_PICTURE);
            if(academyAwards.isPresent() && MoviesConstants.CONSTANT_YES.equalsIgnoreCase(academyAwards.get().getWon())){
                bestPicture.setBestPicture(true);
            } else {
                bestPicture.setBestPicture(false);
            }
        } else{
            log.error("Movie with title :: {} not found ", title);
            throw new MoviesException(MoviesErrorEnum.ERROR_MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return bestPicture;
    }

    @Override
    public void rateMovie(String title, double rating) throws MoviesException {
        Optional<MoviesEntity> entity = moviesRepository.findByTitleIgnoreCase(title);
        if(Objects.nonNull(entity)&& entity.isPresent()){
            MoviesEntity updatedEntity = entity.get();
            updatedEntity.setRating(rating);
            updatedEntity.setUpdatedDate(LocalDateTime.now());
            moviesRepository.save(updatedEntity);
        } else{
            log.error("Movie with title :: {} not found ", title);
            throw new MoviesException(MoviesErrorEnum.ERROR_MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Movies fetchTopRatedMovies() {
        Movies movies = new Movies();
        List<MoviesEntity> topRatedMovies = moviesRepository.findTop10ByOrderByRatingDescBoxOfficeDesc();
        movies.setMovies(CollectionUtils.isEmpty(topRatedMovies) ? new ArrayList<>() : moviesMapper.mapMoviesEntityToMovies(topRatedMovies));
        return movies;
    }

}
