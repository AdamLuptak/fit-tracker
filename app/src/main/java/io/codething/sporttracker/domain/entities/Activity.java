package io.codething.sporttracker.domain.entities;

import io.codething.sporttracker.domain.valueobjects.ActivityType;

import java.time.Duration;
import java.time.LocalDateTime;

public record Activity(
    String id,
    String userId,
    ActivityType.ActivityName type,
    Duration duration,
    LocalDateTime date,
    double calories,
    double distance
) {
}
