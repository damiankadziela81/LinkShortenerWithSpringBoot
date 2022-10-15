package com.example.linkshortener.controller.exception_handling;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
class GlobalControllerAdviser {

    //aby error na stronie byl ladny
    @ApiResponse(description = "Invalid data provided.", content = @Content(examples = @ExampleObject(value = "{\"errorMessage\": \"expirationDate (2014-06-23) must be a future date, email (incorrect_email) must be a well-formed email address\"}")))
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        final BindingResult result = ex.getBindingResult();
        final String fieldErrors = result.getAllErrors()
                .stream()
                .map(error -> error instanceof FieldError fieldError ?
                        fieldError.getField() + " (" + fieldError.getRejectedValue() + ") " + fieldError.getDefaultMessage() :
                        error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return new ExceptionResponse(fieldErrors);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ExceptionResponse handleError(Exception e) {
        return new ExceptionResponse(e.getMessage());
    }
}
