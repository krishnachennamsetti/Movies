package com.assignment.movies.jwt;

import com.assignment.movies.enums.MoviesErrorEnum;
import com.assignment.movies.exception.MoviesError;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtility jwtTokenUtility;

    private final TokenService tokenService;

    private Set<String> skipUrls = new HashSet<>(Arrays.asList("/v1/movies/authenticate", "/swagger-ui.html",
            "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**"));

    private AntPathMatcher pathMatcher = new AntPathMatcher();



    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException {
        try {
            String token = fetchTokenFromAuthHeader(request);
            String userName = jwtTokenUtility.getUsernameFromToken(token);
            if (tokenService.validateUserExists(userName) && !jwtTokenUtility.isTokenExpired(token)) {
                chain.doFilter(request, response);
            } else {
                log.error("Authorization token is invalid for user :: {}", userName);
                throw new MoviesException(MoviesErrorEnum.ERROR_INVALID_AUTH);
            }
        } catch (Exception exception) {
            log.error("Error while authorizing JWT :: {}", exception);
            ObjectMapper mapper = new ObjectMapper();
            MoviesError error = MoviesError.builder().code(MoviesErrorEnum.ERROR_INVALID_AUTH.getCode())
                    .type(MoviesErrorEnum.ERROR_INVALID_AUTH.getType())
                    .message(MoviesErrorEnum.ERROR_INVALID_AUTH.getMessage()).build();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(mapper.writeValueAsString(error));
            response.setContentType("application/json");
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        log.info("skip urls for filter for uri :: {}", request.getServletPath());
        return skipUrls.stream().anyMatch(path -> pathMatcher.match(path, request.getServletPath()));
    }

    /**
     * This method is used to fetch token from authorization header
     *
     * @param request
     * @return
     * @throws MoviesException
     */
    private String fetchTokenFromAuthHeader(HttpServletRequest request) throws MoviesException {
        String authorizationHeader = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            log.error("Authorization header is blank or invalid");
            throw new MoviesException(MoviesErrorEnum.ERROR_INVALID_AUTH);
        }

    }

}
