package com.fabian.PlaylistManagerAPI.exceptions;

import com.fabian.PlaylistManagerAPI.model.api.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(BadPlaylistNameException.class)
    public ErrorResponse handleBadNameException(BadPlaylistNameException exception) {
        exception.printStackTrace();
        return new ErrorResponse("BAD_NAME", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(BadPlaylistNotFoundException.class)
    public ErrorResponse handleNodFoundException(BadPlaylistNotFoundException exception) {
        exception.printStackTrace();
        return new ErrorResponse("NOT_FOUND", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        exception.printStackTrace();
        return new ErrorResponse("500", exception.getMessage());
    }
}