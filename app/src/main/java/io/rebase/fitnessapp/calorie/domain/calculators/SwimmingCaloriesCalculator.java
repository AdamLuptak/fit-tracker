package io.rebase.fitnessapp.calorie.domain.calculators;

public class SwimmingCaloriesCalculator implements CaloriesCalculator {
  private final double weight;

  public SwimmingCaloriesCalculator(double weight) {
    this.weight = weight;
  }

  @Override
  public double calculate(double duration, double distance) {
    var durationInHours = duration / 60.0;
    var speed = distance / durationInHours; // Speed in km/h
    var met = getMET(speed);
    return met * this.weight * durationInHours;
  }

  private double getMET(double speed) {
    if (speed < 2) {
      return 6.0; // Light swimming
    } else if (speed < 3) {
      return 8.0; // Moderate swimming
    } else {
      return 10.0; // Vigorous swimming
    }
  }
}
