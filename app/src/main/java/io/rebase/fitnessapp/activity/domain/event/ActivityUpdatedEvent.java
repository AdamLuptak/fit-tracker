package io.rebase.fitnessapp.activity.domain.event;

public record ActivityUpdatedEvent(String aggregateId, int newDurationMinutes) {}
