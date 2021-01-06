package dev.konstantin.exceptions;

public class UserInfoNotFoundException extends RuntimeException {
    public UserInfoNotFoundException(String pesel) {
        super("No user with " + pesel + " found");
    }
}
