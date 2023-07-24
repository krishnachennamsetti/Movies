package com.assignment.movies.service;


import com.assignment.movies.client.MoviesClient;
import com.assignment.movies.constants.MoviesConstants;
import com.assignment.movies.entity.AcademyAwardsEntity;
import com.assignment.movies.entity.MoviesEntity;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.mapper.MoviesMapper;
import com.assignment.movies.model.MovieOmdbResponse;
import com.assignment.movies.repository.AcademyAwardsRepository;
import com.assignment.movies.repository.MoviesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MoviesServiceImplTest {
    @InjectMocks
    MoviesServiceImpl moviesServiceImpl;

    @Mock
    MoviesRepository moviesRepository;

    @Mock
    AcademyAwardsRepository academyAwardsRepository;

    @Mock
    MoviesMapper mapper;

    @Mock
    MoviesClient client;

    private String movie = "Titanic";


    @Test
    void testCheckOscarBestPictureTrueSuccess() throws MoviesException {
        AcademyAwardsEntity entity = new AcademyAwardsEntity();
        entity.setWon(MoviesConstants.CONSTANT_YES);
        entity.setCategory(MoviesConstants.BEST_PICTURE);
        entity.setNominee(movie);
        when(client.fetchMovieByTitle(movie)).thenReturn(MovieOmdbResponse.builder().title(movie).build());
        when(academyAwardsRepository.findByNomineeAndCategoryIgnoreCase(movie, MoviesConstants.BEST_PICTURE)).thenReturn(Optional.of(entity));
        assertThat(moviesServiceImpl.checkOscarBestPicture(movie).isBestPicture()).isEqualTo(true);
    }

    @Test
    void testCheckOscarBestPictureFalseSuccess() throws MoviesException {
        AcademyAwardsEntity entity = new AcademyAwardsEntity();
        entity.setWon(MoviesConstants.CONSTANT_NO);
        entity.setCategory(MoviesConstants.BEST_PICTURE);
        entity.setNominee(movie);
        when(client.fetchMovieByTitle(movie)).thenReturn(MovieOmdbResponse.builder().title(movie).build());
        when(academyAwardsRepository.findByNomineeAndCategoryIgnoreCase(movie, MoviesConstants.BEST_PICTURE)).thenReturn(Optional.of(entity));
        assertThat(moviesServiceImpl.checkOscarBestPicture(movie).isBestPicture()).isEqualTo(false);
    }

    @Test
    void testCheckOscarBestPictureFailure() {
        when(client.fetchMovieByTitle(movie)).thenReturn(null);
        assertThrows(MoviesException.class,() -> moviesServiceImpl.checkOscarBestPicture(movie));
    }

    @Test
    void testRateMovieSuccess() throws MoviesException {
        MoviesEntity entity = new MoviesEntity();
        entity.setRating(9);
        entity.setTitle(movie);
        entity.setBoxOffice(12345678);
        when(moviesRepository.findByTitleIgnoreCase(movie)).thenReturn(Optional.of(entity));
        moviesServiceImpl.rateMovie(movie,8);
    }

    @Test
    void testRateMovieFailure() {
        when(moviesRepository.findByTitleIgnoreCase(movie)).thenReturn(null);
        assertThrows(MoviesException.class,() ->  moviesServiceImpl.rateMovie(movie,8));
    }

    @Test
    void testFetchTopRatedMovies(){
        MoviesEntity entity = new MoviesEntity();
        entity.setRating(8);
        entity.setTitle(movie);
        entity.setBoxOffice(123456788);
        when(moviesRepository.findTop10ByOrderByRatingDescBoxOfficeDesc()).thenReturn(List.of(entity));
        assertNotNull(moviesServiceImpl.fetchTopRatedMovies());
    }


}
