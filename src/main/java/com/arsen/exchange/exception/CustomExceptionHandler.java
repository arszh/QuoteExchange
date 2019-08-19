package com.arsen.exchange.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        CustomExteptionDetails exteptionDetailsDetails = new CustomExteptionDetails(HttpStatus.BAD_GATEWAY.value(), ex.getMessage(), ExceptionLevel.ERROR);
        logger.error(exteptionDetailsDetails.toString());
        return new ResponseEntity<>(exteptionDetailsDetails, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> NotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getMessage();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        if (!fieldErrors.isEmpty() && fieldErrors.get(0).getDefaultMessage() != null)
            message = fieldErrors.get(0).getDefaultMessage();
        CustomExteptionDetails exteptionDetailsDetails = new CustomExteptionDetails(HttpStatus.BAD_REQUEST.value(), message, ExceptionLevel.ERROR);
        logger.error(exteptionDetailsDetails.toString());
        return new ResponseEntity<>(exteptionDetailsDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<?> invalidFormatHandler(InvalidFormatException ex, WebRequest request) {
        String message = ex.getMessage();
        CustomExteptionDetails exteptionDetailsDetails = new CustomExteptionDetails(HttpStatus.BAD_REQUEST.value(), message, ExceptionLevel.WARM);
        logger.warn(exteptionDetailsDetails.toString());
        return new ResponseEntity<>(exteptionDetailsDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({QuoteNotFoundException.class, ItemNotFoundException.class})
    public ResponseEntity<?> quoteOrItemNotFoundHandler(RuntimeException ex, WebRequest request) {
        CustomExteptionDetails exteptionDetailsDetails = new CustomExteptionDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ExceptionLevel.ERROR);
        logger.error(exteptionDetailsDetails.toString());
        return new ResponseEntity<>(exteptionDetailsDetails, HttpStatus.NOT_FOUND);
    }


}

