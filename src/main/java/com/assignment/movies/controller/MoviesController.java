package com.assignment.movies.controller;


import com.assignment.movies.exception.MoviesError;
import com.assignment.movies.exception.MoviesException;
import com.assignment.movies.model.BestPictureResponse;
import com.assignment.movies.model.Movie;
import com.assignment.movies.model.MovieRatingRequest;
import com.assignment.movies.model.Movies;
import com.assignment.movies.service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/movies")
@Tag(name = "Movies", description = "Movies API endpoints")
@RequiredArgsConstructor
public class MoviesController {

    private final MoviesService moviesService;

    @GetMapping(value = "/{title}/best-picture")
    @Operation(summary = "Checks if a movie won Best Picture Oscar or not", description = "Checks if a movie won Best Picture Oscar or not")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Best picture response", content = @Content(schema = @Schema(implementation = BestPictureResponse.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content(schema = @Schema(implementation = MoviesError.class))) })
    public ResponseEntity<BestPictureResponse> checkBestPicture(
            @PathVariable("title") @Parameter(required = true, example = "Fight Club", description = "Name of the movie") String title) throws MoviesException {
        BestPictureResponse bestPicture = moviesService.checkOscarBestPicture(title);
        return new ResponseEntity<>(bestPicture, HttpStatus.OK);
    }


    @PatchMapping(path = "/{title}/ratings", consumes = "application/json")
    @Operation(summary = "Gives rating for a movie based on the title", description = "Gives rating for a movie based on the title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Rating updated successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content(schema = @Schema(implementation = MoviesError.class))) })
    public ResponseEntity<Void> rateMovie(@PathVariable("title") @Parameter(required = true, example = "Fight Club", description = "Name of the movie")  String title, @RequestBody MovieRatingRequest ratingRequest) throws MoviesException {
        moviesService.rateMovie(title,ratingRequest.getRating());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/top-rated")
    @Operation(summary = "Fetching Top rated movies based on box office", description = "Fetching Top rated movies based on box office")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful", content = @Content(schema = @Schema(implementation = Movies.class))),
            @ApiResponse(responseCode = "400", description = "Movies Error", content = @Content(schema = @Schema(implementation = MoviesError.class))) })
    public ResponseEntity<Movies> fetchTopRatedMovies() {
        Movies movies = moviesService.fetchTopRatedMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


}
