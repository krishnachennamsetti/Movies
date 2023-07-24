package com.assignment.movies.controller;

import com.assignment.movies.entity.UsersEntity;
import com.assignment.movies.exception.MoviesError;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.jwt.JwtTokenUtility;
import com.assignment.movies.model.TokenRequest;
import com.assignment.movies.model.TokenResponse;
import com.assignment.movies.service.TokenService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movies")
@Tag(name = "Token", description = "Token API endpoints")
@Slf4j
@RequiredArgsConstructor
public class TokenController {

    private final JwtTokenUtility tokenUtility;

    private final TokenService tokenService;

    @PostMapping("/authenticate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generation successful", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "Token generation failed", content = @Content(schema = @Schema(implementation = MoviesError.class))) })
    public ResponseEntity<TokenResponse> authenticate(@Valid @RequestBody TokenRequest request)
            throws MoviesException {
        log.info("JWT token generation initiated for user :: {}", request.getUsername());
        UsersEntity userDetails = tokenService.validateUserDetails(request);
        return new ResponseEntity<>(TokenResponse.builder().token(tokenUtility.generateToken(userDetails.getUsername())).build(),
                HttpStatus.OK);
    }



}
