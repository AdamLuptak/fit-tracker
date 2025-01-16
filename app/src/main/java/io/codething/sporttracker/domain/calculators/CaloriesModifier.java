package io.codething.sporttracker.domain.calculators;

public interface CaloriesModifier {
    double modify(double baseCalories, double duration, double distance);
}
