package com.assignment.movies.service;

import com.assignment.movies.entity.UsersEntity;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.model.TokenRequest;

public interface TokenService {

    /**
     * This method is used to fetch the user details from database based on username and password
     * and validates if the combination exists or not
     * @param request TokenRequest object
     * @return UsersEntity object
     * @throws MoviesException
     */
    UsersEntity validateUserDetails(TokenRequest request) throws MoviesException;

    /**
     * This method check whether uer exists based on username or not
     * @param username
     * @return true if user exists and false if doesn't
     */
    boolean validateUserExists(String username);
}
