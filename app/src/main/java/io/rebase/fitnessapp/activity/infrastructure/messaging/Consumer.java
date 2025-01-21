package io.rebase.fitnessapp.activity.infrastructure.messaging;

import io.rebase.fitnessapp.shared.eventstore.Event;

public interface Consumer<T> {
  default void accept(Event event) {
    accept((T) event.payload());
  }

  void accept(T event);
}
