package com.assignment.movies.enums;


public enum MoviesErrorEnum {

    ERROR_MOVIE_NOT_FOUND("FUNCTIONAL","ERROR_MOVIE_NOT_FOUND","Movie not found"),
    ERROR_INPUT_VALIDATION("TECHNICAL","ERROR_INPUT_VALIDATION","Input validation"),
    ERROR_INVALID_AUTH("TECHNICAL","ERROR_INVALID_AUTH","Invalid Authorization Token"),
    ERROR_INVALID_USER("FUNCTIONAL","ERROR_INVALID_USER","User details are invalid"),
    TECHNICAL_ERROR("TECHNICAL","TECHNICAL_ERROR","Technical Failure");

    String type;

    String code;

    String message;

    private MoviesErrorEnum(String type, String code, String message) {
        this.type = type;
        this.code = code;
        this.message = message;
    }


    public String getType() {
        return type;
    }


    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}
