package io.rebase.fitnessapp.usermanagement.domain.model;

import io.rebase.fitnessapp.calorie.domain.calculators.BMRCalculator;

public record User(String id, Gender gender, String name, int age, double weight, double height) {
  public enum Gender {
    MALE,
    FEMALE
  }

  public double bmr() {
    BMRCalculator calculator = new BMRCalculator();
    return calculator.calculate(this);
  }
}
