package io.rebase.fitnessapp.activity.application.services;

import io.rebase.fitnessapp.activity.application.command.CreateActivityCommand;
import io.rebase.fitnessapp.activity.application.command.CreateActivityUseCase;
import io.rebase.fitnessapp.activity.application.validation.CreateActivityCommandValidator;

public class ActivityTrackerService {

  private final CreateActivityUseCase createActivityUseCase;
  private final CreateActivityCommandValidator createActivityCommandValidator;

  public ActivityTrackerService(
      CreateActivityUseCase createActivityUseCase,
      CreateActivityCommandValidator createActivityCommandValidator) {
    this.createActivityUseCase = createActivityUseCase;
    this.createActivityCommandValidator = createActivityCommandValidator;
  }

  public void addActivity(CreateActivityCommand createActivityCommand) {
    createActivityCommandValidator.validate(createActivityCommand);
    createActivityUseCase.execute(createActivityCommand);
  }
}
