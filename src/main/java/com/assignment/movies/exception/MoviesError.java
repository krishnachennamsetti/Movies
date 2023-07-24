package com.assignment.movies.exception;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoviesError implements Serializable {

    @Schema(description = "Type of Error TECHNICAL/FUNCTIONAL",example = "TECHNICAL")
    private String type;

    @Schema(description = "Error code for each specific error",example = "ERROR_TECHNICAL_FAILURE")
    private String code;

    @Schema(description = "Error message description",example = "Technical Failure")
    private String message;
}
