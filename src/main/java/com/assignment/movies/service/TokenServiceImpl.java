package com.assignment.movies.service;

import com.assignment.movies.entity.UsersEntity;
import com.assignment.movies.enums.MoviesErrorEnum;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.model.TokenRequest;
import com.assignment.movies.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final UsersRepository usersRepository;
    @Override
    public UsersEntity validateUserDetails(TokenRequest request) throws MoviesException {
        Optional<UsersEntity> userDetails = usersRepository.findByUsernameAndCredential(request.getUsername(),request.getPassword());
        if(Objects.nonNull(userDetails) && userDetails.isPresent()){
            return userDetails.get();
        } else{
            log.error("Unable to fetch the user details for username :: {}", request.getUsername());
            throw new MoviesException(MoviesErrorEnum.ERROR_INVALID_USER);
        }
    }

    @Override
    public boolean validateUserExists(String username) {
        Optional<UsersEntity> userDetails = usersRepository.findByUsername(username);
        return Objects.nonNull(userDetails) && userDetails.isPresent();
    }
}
