package com.assignment.movies.model;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class Rating implements Serializable {

    private String source;

    private String value;
}
