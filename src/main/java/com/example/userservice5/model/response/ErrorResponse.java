package com.example.userservice5.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {

    private String status;
    private String message;
    private String info;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> errors = new HashMap<>();

    public void addError(String field, String message){
        errors.put(field, message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }
}