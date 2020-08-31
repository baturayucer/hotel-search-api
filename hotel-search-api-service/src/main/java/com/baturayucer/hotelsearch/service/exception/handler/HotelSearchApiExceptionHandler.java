package com.baturayucer.hotelsearch.service.exception.handler;

import com.baturayucer.hotelsearch.service.exception.AdvertSearchException;
import com.baturayucer.hotelsearch.service.exception.AdvertUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Global Exception Handler class for Hotel Search Api.
 * @author baturayucer.
 */
@ControllerAdvice
public class HotelSearchApiExceptionHandler {

    @ExceptionHandler(value = AdvertSearchException.class)
    public ResponseEntity<Object> advertSearchException(AdvertSearchException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AdvertUpdateException.class)
    public ResponseEntity<Object> advertUpdateException(AdvertUpdateException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}