package io.rebase.fitnessapp.shared.exceptions;

public class ActivityNotFoundException extends RuntimeException {
  public ActivityNotFoundException(String userId) {
    super("User with ID " + userId + " was not found.");
  }
}
