package com.assignment.movies.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class Movie implements Serializable {

    @Schema(description = "Title of the movie",example = "Titanic")
    private String title;

    @Schema(description = "Box-office value of the movie",example = "23456666")
    private double boxOffice;

    @Schema(description = "User rating of the movie",example = "9.5")
    private double rating;
}
