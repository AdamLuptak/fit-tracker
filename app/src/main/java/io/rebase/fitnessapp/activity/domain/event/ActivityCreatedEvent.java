package io.rebase.fitnessapp.activity.domain.event;

import io.rebase.fitnessapp.activity.domain.model.Distance;
import java.time.Duration;

public record ActivityCreatedEvent(
    String activityId, String userId, String activityType, Duration duration, Distance distance) {}
