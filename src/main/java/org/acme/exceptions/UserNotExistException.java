package org.acme.exceptions;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String message) {
        super(message);
    }
    
}
