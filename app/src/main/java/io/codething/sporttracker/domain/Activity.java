package io.codething.sporttracker.domain;

import java.time.Duration;
import java.time.LocalDateTime;

public record Activity(
    String id,
    String userId,
    ActivityType.ActivityName type,
    Duration duration,
    LocalDateTime date,
    double baseCalories,
    double calories,
    double distance
) {
}
