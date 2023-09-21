package com.rehome.chat.exception;

public class ChatBadRequestException extends RuntimeException {
    String errorMessage;

    public ChatBadRequestException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
