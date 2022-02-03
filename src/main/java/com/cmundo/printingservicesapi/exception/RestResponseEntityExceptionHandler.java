package com.cmundo.printingservicesapi.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceNotCreatedException.class})
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    public ResponseEntity<Object> handleHttpResourceNotCreatedException(ResourceNotCreatedException exception){

        log.error("ResourceNotCreatedException ", exception);
        Error error = new Error();
        error.setCode("RESOURCE_NOT_CREATED");
        error.setMessage(exception.getMessage());

        log.error("handleHttpResourceNotCreatedException(): " + error);
        return new ResponseEntity<>(error, createHeader(), HttpStatus.NOT_IMPLEMENTED);
    }

    private HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        return headers;
    }
}
