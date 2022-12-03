package com.Salas.Automotores.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.Salas.Automotores.controllers.dtos.ResponseDto;
import com.Salas.Automotores.exceptions.BadRequestException;
import com.Salas.Automotores.exceptions.ListNotFoundException;
import com.Salas.Automotores.exceptions.RecordNotExistException;
import com.Salas.Automotores.exceptions.NotAuthorizatedException;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(ListNotFoundException.class)
    public ResponseDto handleListNotFoundException(ListNotFoundException e) { 
    	return new ResponseDto(204, e.getMessage()); 
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotExistException.class)
    public ResponseDto handleRecordNotExistException(RecordNotExistException e) { 
    	return new ResponseDto(400, e.getMessage()); 
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotAuthorizatedException.class)
    public ResponseDto handleNotAuthorizatedException(NotAuthorizatedException e) {
        return new ResponseDto(403, e.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseDto handleBadRequestException(BadRequestException e) {
    	return new ResponseDto(404, e.getMessage());
    }

}
