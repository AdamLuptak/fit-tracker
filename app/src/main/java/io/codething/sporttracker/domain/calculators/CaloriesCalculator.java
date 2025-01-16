package io.codething.sporttracker.domain.calculators;

import java.time.Duration;

public interface CaloriesCalculator {
    double calculateCalories(double duration, double distance);
}
