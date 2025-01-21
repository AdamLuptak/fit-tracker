package io.rebase.fitnessapp.stats.domain.model;

import io.rebase.fitnessapp.activity.domain.model.Distance;
import java.time.Duration;
import java.util.List;

public record UserActivityReport(
    String userId,
    String activityType,
    Distance distance,
    Duration duration,
    double baseCalories,
    double modifiedCalories,
    List<String> calorieModifiers) {}
