package io.codething.sporttracker.domain.calculators;

public class RunningCaloriesCalculator implements CaloriesCalculator {
    private final double weight;

    public RunningCaloriesCalculator(double weight) {
        this.weight = weight;
    }

    @Override
    public double calculateCalories(double duration, double distance) {
        double durationInHours = duration / 60.0;
        double speed = distance / durationInHours;
        double met = getMET(speed);
        return met * this.weight * durationInHours;
    }

    private double getMET(double speed) {
        if (speed < 6) {
            return 4.0; // Walking or slow jogging
        } else if (speed < 10) {
            return 9.0; // Moderate running
        } else {
            return 12.0; // Fast running or sprinting
        }
    }
}