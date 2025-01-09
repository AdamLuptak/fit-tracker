package io.codething.calculators;

public interface CalorieModifier {
    double modify(double baseCalories, double duration, double distance);
}
