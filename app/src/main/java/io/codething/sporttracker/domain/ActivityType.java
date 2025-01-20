package io.codething.sporttracker.domain;

import io.codething.sporttracker.domain.calculators.CaloriesCalculator;

import java.time.Duration;

public record ActivityType(ActivityName name, CaloriesCalculator calculator) {

    public enum ActivityName {
        RUNNING,
        SWIMMING,
        WALKING;
    }

    public double calculateCalories(Duration duration, double distance) {
        return calculator.calculateCalories(duration.toMinutes(), distance);
    }
}

