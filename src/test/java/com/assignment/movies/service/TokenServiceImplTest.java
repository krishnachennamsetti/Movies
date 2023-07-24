package com.assignment.movies.service;


import com.assignment.movies.entity.UsersEntity;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.model.TokenRequest;
import com.assignment.movies.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenServiceImplTest {

    @InjectMocks
    TokenServiceImpl tokenServiceImpl;

    @Mock
    UsersRepository usersRepository;

    @Test
    void testValidateUserDetailsSuccess() throws MoviesException {
        UsersEntity entity = new UsersEntity();
        entity.setUsername("admin");
        entity.setCredential("admin123");
        when(usersRepository.findByUsernameAndCredential(entity.getUsername(),entity.getCredential())).thenReturn(Optional.of(entity));
        assertThat(tokenServiceImpl.validateUserDetails(TokenRequest.builder().username(entity.getUsername()).password(entity.getCredential()).build()).getUsername()).isEqualTo(entity.getUsername());
    }


    @Test
    void testValidateUserDetailsFailure() {
        UsersEntity entity = new UsersEntity();
        entity.setUsername("admin");
        entity.setCredential("admin123");
        when(usersRepository.findByUsernameAndCredential(entity.getUsername(),entity.getCredential())).thenReturn(null);
        assertThrows(MoviesException.class,() -> tokenServiceImpl.validateUserDetails(TokenRequest.builder().username(entity.getUsername()).password(entity.getCredential()).build()).getUsername());
    }

    @Test
    void testValidateUserExistsSuccess() {
        UsersEntity entity = new UsersEntity();
        entity.setUsername("admin");
        entity.setCredential("admin123");
        when(usersRepository.findByUsername(entity.getUsername())).thenReturn(Optional.of(entity));
        assertThat(tokenServiceImpl.validateUserExists(entity.getUsername())).isEqualTo(true);
    }

    @Test
    void testValidateUserExistsFailure() {
        when(usersRepository.findByUsername("user")).thenReturn(null);
        assertThat(tokenServiceImpl.validateUserExists("user")).isEqualTo(false);
    }


}
