package com.assignment.movies.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestPictureResponse implements Serializable {

    @Schema(description = "Movie won best picture oscar or not",example = "true")
    private boolean bestPicture;
}
