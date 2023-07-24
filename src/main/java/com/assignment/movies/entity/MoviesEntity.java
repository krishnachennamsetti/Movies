package com.assignment.movies.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "Movies")
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MoviesEntity {
    @Id
    @Schema(description = "Unique movie id",example = "84898345896796798")
    private String id;

    @Schema(description = "Title of the movie",example = "Titanic")
    private String title;

    @Schema(description = "Collections of the movie",example = "Titanic")
    private double boxOffice;

    @Schema(description = "User rating for the movie",example = "4.5")
    private double rating;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(description = "created date",example = "2022-07-18T19:36:49.588Z")
    private LocalDateTime createdDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(description = "updated date",example = "2022-07-18T19:36:49.588Z")
    private LocalDateTime updatedDate;


}
