package io.rebase.fitnessapp.shared.eventstore;

import io.rebase.fitnessapp.activity.infrastructure.messaging.Consumer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryEventStore implements EventStore {
  private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryEventStore.class);

  private final Map<Class<?>, List<Consumer<?>>> listeners = new ConcurrentHashMap<>();
  private final Map<String, List<Event>> eventStreams = new ConcurrentHashMap<>();

  @Override
  public void registerListener(Class<?> eventType, Consumer<?> listener) {
    listeners.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(listener);
  }

  @Override
  public void append(String aggregateId, Event event) {
    LOGGER.info("Appending event: {} for aggregate: {}", event, aggregateId);
    eventStreams.computeIfAbsent(aggregateId, k -> new ArrayList<>()).add(event);

    List<Consumer<?>> eventListeners = listeners.get(event.payload().getClass());
    if (eventListeners != null) {
      for (Consumer<?> listener : eventListeners) {
        listener.accept(event);
      }
    }
  }

  @Override
  public List<Event> load(String aggregateId) {
    LOGGER.info("Loading events for aggregate: {}", aggregateId);
    return eventStreams.getOrDefault(aggregateId, Collections.emptyList());
  }

  @Override
  public List<Event> loadAll() {
    LOGGER.info("Loading all events from the store");
    List<Event> allEvents = new ArrayList<>();
    for (List<Event> events : eventStreams.values()) {
      allEvents.addAll(events);
    }
    return allEvents;
  }
}
