package com.example.simplespringbootrestapi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response<T> {
    private String errorMessage;
    private T data;
}
