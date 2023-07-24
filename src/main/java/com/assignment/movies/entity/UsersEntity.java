package com.assignment.movies.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UsersEntity {

    @Id
    @Schema(description = "Unique movie id",example = "84898345896796798")
    private String id;

    @Schema(description = "username of the customer",example = "admin")
    private String username;

    @Schema(description = "username of the customer",example = "admin123")
    private String credential;
}
