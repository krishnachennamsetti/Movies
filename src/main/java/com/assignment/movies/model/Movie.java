package com.assignment.movies.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class Movie implements Serializable {

    private String title;
    private double boxOffice;
    private double rating;
}
