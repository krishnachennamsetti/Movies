package com.assignment.movies.model;

import com.assignment.movies.constants.MoviesConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest implements Serializable {


    @NotBlank(message = "username not specified")
    @Size(min=1,max=50, message = "username length is invalid")
    @Pattern(regexp = MoviesConstants.REGEX_AUTH,message = "username is invalid")
    @Schema(description = "username of user",example = "admin", required = true)
    private String username;

    @NotBlank(message = "password not specified")
    @Size(min=1,max=50, message = "password length is invalid")
    @Pattern(regexp = MoviesConstants.REGEX_AUTH,message = "password is invalid")
    @Schema(description = "password of user",example = "admin123", required = true)
    private String password;


}
