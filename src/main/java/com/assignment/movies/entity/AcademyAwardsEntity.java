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

@Document(collection = "AcademyAwards")
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AcademyAwardsEntity {

    @Id
    @Schema(description = "Unique movie id",example = "84898345896796798")
    private String id;

    @Schema(description = "Name of nominee",example = "Titanic")
    private String nominee;

    @Schema(description = "Category",example = "Best Picture")
    private String category;

    @Schema(description = "Year of Oscar",example = "2009")
    private String year;

    @Schema(description = "Additional info",example = "20")
    private String additionalInfo;

    @Schema(description = "Won the academy award or not?",example = "Yes")
    private String won;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(description = "created date",example = "2022-07-18T19:36:49.588Z")
    private LocalDateTime createdDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Schema(description = "updated date",example = "2022-07-18T19:36:49.588Z")
    private LocalDateTime updatedDate;


}
