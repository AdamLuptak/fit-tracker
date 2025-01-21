package io.rebase.fitnessapp.shared.eventstore;

import io.rebase.fitnessapp.activity.infrastructure.messaging.Consumer;
import java.util.List;

public interface EventStore {
  void registerListener(Class<?> eventType, Consumer<?> listener);

  void append(String aggregateId, Event event);

  List<Event> load(String aggregateId);

  List<Event> loadAll();
}
