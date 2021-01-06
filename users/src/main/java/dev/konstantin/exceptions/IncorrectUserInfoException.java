package dev.konstantin.exceptions;

public class IncorrectUserInfoException extends RuntimeException {
  public IncorrectUserInfoException(String message) {
    super(message);
  }
}
