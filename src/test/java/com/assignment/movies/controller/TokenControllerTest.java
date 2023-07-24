package com.assignment.movies.controller;


import com.assignment.movies.entity.UsersEntity;
import com.assignment.movies.jwt.JwtTokenUtility;
import com.assignment.movies.model.TokenRequest;
import com.assignment.movies.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenControllerTest {

    @InjectMocks
    TokenController tokenController;

    @Mock
    TokenService tokenService;

    @Mock
    JwtTokenUtility utility;


    @Test
    void testAuthenticate_Success() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername("admin");
        TokenRequest request = TokenRequest.builder().username("admin").password("admin123").build();
        when(tokenService.validateUserDetails(request)).thenReturn(usersEntity);
        when(utility.generateToken(usersEntity.getUsername())).thenReturn("Bearer token");
        assertThat(tokenController.authenticate(request).getStatusCode()).isEqualTo(HttpStatus.OK);
    }


}
