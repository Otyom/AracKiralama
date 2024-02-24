package com.example.AracKiralama.exception;

import lombok.Builder;

@Builder
public class ErrorMessage {
    public int statusCode;
    public String message;
}
