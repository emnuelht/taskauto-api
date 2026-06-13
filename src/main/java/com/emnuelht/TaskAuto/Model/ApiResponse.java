package com.emnuelht.TaskAuto.Model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiResponse<T> {
    private final Boolean success;
    private final String message;
    private final Integer status;
    private final T data;
    private final List<String> errors;
    private final LocalDateTime timestamp;

    private ApiResponse(Boolean success, String message, T data, List<String> errors, Integer status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status = status;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data, null, 200);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(true, "OK", null, null, 200);
    }

    public static <T> ApiResponse<T> error(String message, Integer status) {
        return new ApiResponse<>(false, message, null, null, status);
    }

    public static <T> ApiResponse<T> error(List<String> errors, Integer status) {
        return new ApiResponse<>(false, null, null, errors, status);
    }
}
