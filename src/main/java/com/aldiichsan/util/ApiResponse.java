package com.aldiichsan.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int statusCode;
    private String status;
    private String message;
    private T total;
    private T data;
    private T error;

    // For Insert and Update
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatusCode(status.value());
        response.setStatus(status.getReasonPhrase());
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    // For Get
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T total, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatusCode(status.value());
        response.setStatus(status.getReasonPhrase());
        response.setMessage(message);
        response.setTotal(total);
        response.setData(data);
        return response;
    }

    // For Delete
    public static <T> ApiResponse<T> success(HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatusCode(status.value());
        response.setStatus(status.getReasonPhrase());
        response.setMessage(message);
        return response;
    }

    public static <T> ApiResponse<T> fail(HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatusCode(status.value());
        response.setStatus(status.getReasonPhrase());
        response.setMessage(message);
        return response;
    }
}
