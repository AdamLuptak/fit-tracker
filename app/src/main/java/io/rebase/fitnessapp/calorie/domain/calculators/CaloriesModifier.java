package io.rebase.fitnessapp.calorie.domain.calculators;

public interface CaloriesModifier {
  double modify(double baseCalories, double duration, double distance);
}
