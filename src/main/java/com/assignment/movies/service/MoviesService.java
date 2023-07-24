package com.assignment.movies.service;

import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.model.BestPictureResponse;
import com.assignment.movies.model.Movies;

public interface MoviesService {

    /**
     * This method is used to check whether a movie won best picture oscar or not
     * @param title
     * @return returns BestPictureResponse object with bestPictureWon as true or false
     * @throws MoviesException
     */
    BestPictureResponse checkOscarBestPicture(String title) throws MoviesException;

    /**
     * This method is used to update the mov ie rating based on movie title in database
     * @param movieName
     * @param rating
     * @throws MoviesException
     */
    void rateMovie(String movieName, double rating) throws MoviesException;

    /**
     * This method is used to fetch the Top 10 rated movies based on their box office value
     * @return Top-rated movies list based on boxoffice value
     */
    Movies fetchTopRatedMovies();
}
