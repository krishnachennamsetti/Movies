package com.assignment.movies.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class TokenResponse implements Serializable{

    @Schema(description = "Generated JWT",example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWNpcGVzYWRtaW4iLCJpYXQiOjE2NTgxNjYxNDksImV4cCI6MTY1ODM0NjE0OX0.K9xOGOzVYSO7myuAPoXOfMQdSKTjz0E_Kwg4-Co8X0k")
    private String token;

}
