package com.project.exception;

public class DaoException extends RuntimeException {
    public DaoException(String message) {
        super(message);
    }
}