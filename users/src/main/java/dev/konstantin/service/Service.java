package dev.konstantin.service;

import java.util.regex.Pattern;

// Class contains method which uses in InMemory and UserServiceImpl
public class Service {
  // if string is ok returns true
  protected boolean isStringContainOnlyAlphabetic(String string) {

    for (int i = 0; i < string.length(); i++) {
      if (!Character.isAlphabetic(string.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  // if email is ok returns true
  protected boolean emailChecker(String email) {
    return Pattern.matches("^(.+)@(.+)$", email);
    // "^(.+)@(.+)$" simpliest
    // "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$"
  }
}
