package com.assignment.movies.client;

import com.assignment.movies.constants.MoviesConstants;
import com.assignment.movies.model.MovieOmdbResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoviesClient {

    @Value("${movies.omdb.url:''}")
    private String moviesUrl;

    @Value("${movies.omdb.api-key:''}")
    private String apiKey;

    /**
     * This method is used to make a webclient call to omdb API to fetch the movie details based on title.
     * @param title
     * @return
     */
    public MovieOmdbResponse fetchMovieByTitle(String title){
        log.info("Fetching the movie details from omdb for title :: {}",title);
        MultiValueMap<String,String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add(MoviesConstants.API_KEY,apiKey);
        queryParams.add(MoviesConstants.MOVIE_TITLE,title);
        WebClient client = WebClient.builder().baseUrl(moviesUrl).build();
       return client.get().uri(uri -> uri.queryParams(queryParams).build()).retrieve().bodyToMono(MovieOmdbResponse.class).block();
    }
}
