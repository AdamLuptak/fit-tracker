package io.rebase.fitnessapp.activity.application.command;

import io.rebase.fitnessapp.activity.domain.event.ActivityCreatedEvent;
import io.rebase.fitnessapp.activity.domain.model.Activity;
import io.rebase.fitnessapp.activity.infrastructure.repositories.ActivityRepository;
import io.rebase.fitnessapp.shared.IdGenerator;
import io.rebase.fitnessapp.shared.eventstore.Event;
import io.rebase.fitnessapp.shared.eventstore.EventStore;
import java.time.Clock;

public class CreateActivityUseCase {
  private final ActivityRepository activityRepository;
  private final EventStore eventStore;
  private final IdGenerator idGenerator;
  private final Clock clock;

  public CreateActivityUseCase(
      ActivityRepository activityRepository,
      EventStore eventStore,
      IdGenerator idGenerator,
      Clock clock) {
    this.activityRepository = activityRepository;
    this.eventStore = eventStore;
    this.idGenerator = idGenerator;
    this.clock = clock;
  }

  public void execute(CreateActivityCommand command) {
    Activity activity =
        new Activity(
            idGenerator.generate(),
            command.userId(),
            command.activityType(),
            command.duration(),
            command.distance());

    activityRepository.save(activity);

    eventStore.append(
        activity.id(),
        new Event(
            new ActivityCreatedEvent(
                activity.id(),
                activity.userId(),
                activity.type(),
                activity.duration(),
                activity.distance()),
            clock.instant()));
  }
}
