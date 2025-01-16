package io.codething.sporttracker.domain.calculators;

public class WalkingCaloriesCalculator implements CaloriesCalculator {
    private final double weight;

    public WalkingCaloriesCalculator(double weight) {
        this.weight = weight;
    }

    @Override
    public double calculateCalories(double duration, double distance) {
        var durationInHours = duration / 60.0;
        var speed = distance / durationInHours; // Speed in km/h
        var met = getMET(speed);
        return met * this.weight * durationInHours;
    }

    private double getMET(double speed) {
        if (speed < 4) {
            return 2.5; // Slow walking
        } else if (speed < 6) {
            return 3.5; // Moderate walking
        } else {
            return 5.0; // Fast walking
        }
    }
}
