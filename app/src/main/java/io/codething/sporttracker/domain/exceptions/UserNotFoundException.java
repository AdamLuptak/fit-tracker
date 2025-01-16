package io.codething.sporttracker.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("User with ID " + userId + " was not found.");
    }
}
