package com.assignment.movies.exception;

import com.assignment.movies.enums.MoviesErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data
@EqualsAndHashCode(callSuper = false)
public class MoviesException extends Exception {

    private String type;

    private String code;

    private String message;

    private HttpStatus status;

    public MoviesException(MoviesErrorEnum errorEnum) {
        super();
        this.code = errorEnum.getCode();
        this.type = errorEnum.getType();
        this.message = errorEnum.getMessage();
    }

    public MoviesException(MoviesErrorEnum errorEnum, HttpStatus status) {
        super();
        this.code = errorEnum.getCode();
        this.type = errorEnum.getType();
        this.message = errorEnum.getMessage();
        this.status = status;
    }

    public MoviesException(MoviesErrorEnum errorEnum, String parameter) {
        super();
        this.code = errorEnum.getCode();
        this.type = errorEnum.getType();
        this.message = errorEnum.getMessage().replace("[param]", parameter);
    }
}
