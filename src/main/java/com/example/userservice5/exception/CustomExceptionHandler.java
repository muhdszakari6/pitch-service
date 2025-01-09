package com.example.userservice5.exception;

import com.example.userservice5.model.response.ApiError;
import com.example.userservice5.model.response.ErrorResponse;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUncaughtException(Exception ex){
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage(ex.getMessage());
        error.setTimeStamp(new Date());
        return ResponseEntity.status(error.getStatus()).contentType(MediaType.APPLICATION_JSON).body(error);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleCaughtException(ApiException ex){
        ApiError error = new ApiError();
        error.setStatus(ex.getStatus());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(new Date());
        return ResponseEntity.status(error.getStatus()).contentType(MediaType.APPLICATION_JSON).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(String.valueOf(status.value()));
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setInfo(ex.getLocalizedMessage());

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errorResponse.addError(fieldName, errorMessage);
        });

        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(errorResponse);
    }

}
