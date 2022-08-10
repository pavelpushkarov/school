package com.example.school.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class Result<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reason;

    public boolean hasData() {
        return data == null;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> response = new Result<>();
        response.data = data;
        return response;
    }

    public static <T> Result<T> error(String reason) {
        Result<T> response = new Result<>();
        response.reason = reason;
        return response;
    }
}
