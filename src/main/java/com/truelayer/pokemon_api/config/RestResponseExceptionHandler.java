package com.truelayer.pokemon_api.config;


import com.truelayer.pokemon_api.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class RestResponseExceptionHandler {

    private static final Logger logger = Logger.getLogger(RestResponseExceptionHandler.class.getName());

    @ExceptionHandler(value = { ValidationException.class })
    protected ResponseEntity<String> handleValidation(ValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpClientErrorException.class })
    protected ResponseEntity<String> handleClientException(HttpClientErrorException ex) {
        logger.log(Level.SEVERE, "A client error occurred {0}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<String> handleGenericException(Exception ex) {
        logger.log(Level.SEVERE, "An error occurred {0}", ex.getMessage());
        return new ResponseEntity<>("Generic error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
