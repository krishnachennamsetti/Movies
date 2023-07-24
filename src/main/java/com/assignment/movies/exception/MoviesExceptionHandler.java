package com.assignment.movies.exception;

import com.assignment.movies.enums.MoviesErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;


@ControllerAdvice
public class MoviesExceptionHandler {
    /**
     * This method is used to frame the generic error
     *
     * @param exception
     * @param status
     * @return
     */
    private ResponseEntity<MoviesError> frameGenericError(Exception exception, HttpStatus status) {
        MoviesErrorEnum error = MoviesErrorEnum.TECHNICAL_ERROR;
        MoviesError moviesError = new MoviesError(error.getType(), error.getCode(), exception.getMessage());
        return ResponseEntity.status(status).body(moviesError);
    }

    /**
     * This method is used to frame the application related exceptions
     *
     * @param exception
     * @param status
     * @return
     */
    private ResponseEntity<MoviesError> frameApplicationError(MoviesException exception, HttpStatus status) {
        MoviesError error = new MoviesError(exception.getType(), exception.getCode(), exception.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    /**
     * This method is used to frame the application related exceptions
     *
     * @param exception
     * @param status
     * @return
     */
    private ResponseEntity<MoviesError> frameRuntimeApplicationError(MoviesRuntimeException exception,
                                                                      HttpStatus status) {
        MoviesError error = new MoviesError(exception.getType(), exception.getCode(), exception.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MoviesException.class)
    public ResponseEntity<MoviesError> handleMoviesException(MoviesException exception) {
        return frameApplicationError(exception,
                Objects.nonNull(exception.getStatus()) ? exception.getStatus() : HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MoviesRuntimeException.class)
    public ResponseEntity<MoviesError> handleMoviesRuntimeException(MoviesRuntimeException exception) {
        return frameRuntimeApplicationError(exception,
                Objects.nonNull(exception.getStatus()) ? exception.getStatus() : HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMoviesException(MethodArgumentNotValidException exception) {
        MoviesErrorEnum errorEnum = MoviesErrorEnum.ERROR_INPUT_VALIDATION;
        MoviesError error = new MoviesError(errorEnum.getType(), errorEnum.getCode(),
                exception.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MoviesError> handleGenericException(Exception exception) {
        return frameGenericError(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
