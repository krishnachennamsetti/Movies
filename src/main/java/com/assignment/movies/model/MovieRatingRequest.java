package com.assignment.movies.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRatingRequest implements Serializable {

    @Positive
    @Schema(description = "user rating for the movie",example = "9.5", required = true)
    private double rating;

}
