package com.assignment.movies.model;


import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BestPictureResponse implements Serializable {

    private boolean bestPicture;
}
