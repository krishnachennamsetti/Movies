package com.assignment.movies.model;


import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating implements Serializable {

    private String source;

    private String value;
}
