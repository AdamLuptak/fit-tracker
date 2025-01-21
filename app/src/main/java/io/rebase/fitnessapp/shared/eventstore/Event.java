package io.rebase.fitnessapp.shared.eventstore;

import java.time.Instant;

public record Event(Object payload, Instant timestamp) {}
