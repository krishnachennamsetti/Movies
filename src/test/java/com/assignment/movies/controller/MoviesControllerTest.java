package com.assignment.movies.controller;


import com.assignment.movies.model.BestPictureResponse;
import com.assignment.movies.model.Movie;
import com.assignment.movies.model.MovieRatingRequest;
import com.assignment.movies.model.Movies;
import com.assignment.movies.service.MoviesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MoviesControllerTest {

    @InjectMocks
    MoviesController controller;

    @Mock
    MoviesService moviesService;

    @Test
    void testCheckBestPicture() throws Exception{
        when(moviesService.checkOscarBestPicture("Titanic")).thenReturn(BestPictureResponse.builder().bestPicture(true).build());
        assertThat(controller.checkBestPicture("Titanic").getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void testFetchTopRatedMovies() throws Exception{
        when(moviesService.fetchTopRatedMovies()).thenReturn(Movies.builder().movies(List.of(Movie.builder().rating(9.5).title("Avatar").boxOffice(1234567).build())).build());
        assertThat(controller.fetchTopRatedMovies().getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testRateMovie() throws Exception{
        assertThat(controller.rateMovie("Avatar", MovieRatingRequest.builder().rating(9).build()).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
