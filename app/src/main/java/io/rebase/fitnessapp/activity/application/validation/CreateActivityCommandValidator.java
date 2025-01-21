package io.rebase.fitnessapp.activity.application.validation;

import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.SUPPORTED_ACTIVITY_TYPES;

import io.rebase.fitnessapp.activity.application.command.CreateActivityCommand;
import io.rebase.fitnessapp.usermanagement.application.service.UserService;

public class CreateActivityCommandValidator {
  private final UserService userRepository;

  public CreateActivityCommandValidator(UserService userRepository) {
    this.userRepository = userRepository;
  }

  public void validate(CreateActivityCommand command) {
    if (command.userId() == null) {
      throw new IllegalArgumentException("User ID cannot be null.");
    }

    userRepository
        .findById(command.userId())
        .orElseThrow(() -> new IllegalArgumentException("User not found."));

    if (command.activityType() == null) {
      throw new IllegalArgumentException("Activity type cannot be null.");
    }
    if (!SUPPORTED_ACTIVITY_TYPES.contains(command.activityType())) {
      throw new IllegalArgumentException("Activity type cannot be null.");
    }
    if (command.duration() == null) {
      throw new IllegalArgumentException("Duration cannot be null.");
    }
    if (command.distance() == null) {
      throw new IllegalArgumentException("Distance cannot be null.");
    }
  }
}
