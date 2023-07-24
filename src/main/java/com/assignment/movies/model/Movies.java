package com.assignment.movies.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Movies implements Serializable {

    private List<Movie> movies;

}
