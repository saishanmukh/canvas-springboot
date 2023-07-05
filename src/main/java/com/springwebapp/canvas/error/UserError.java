package com.springwebapp.canvas.error;


public class UserError extends Exception {
    public enum ErrorType {
        USER_ALREADY_EXISTS,
        USER_NOT_FOUND,
        // Add more error types as needed
    }

    private ErrorType errorType;

    public UserError(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}


