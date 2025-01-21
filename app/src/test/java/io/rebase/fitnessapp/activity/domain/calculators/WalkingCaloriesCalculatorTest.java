package io.rebase.fitnessapp.activity.domain.calculators;

import static org.junit.jupiter.api.Assertions.*;

import io.rebase.fitnessapp.calorie.domain.calculators.WalkingCaloriesCalculator;
import org.junit.jupiter.api.Test;

class WalkingCaloriesCalculatorTest {
  @Test
  void testCalculateSlowWalking() {
    WalkingCaloriesCalculator calculator = new WalkingCaloriesCalculator(70);
    double calories = calculator.calculate(30, 2);
    assertEquals(122.5, calories, 0.1);
  }

  @Test
  void testCalculateModerateWalking() {
    WalkingCaloriesCalculator calculator = new WalkingCaloriesCalculator(70);
    double calories = calculator.calculate(30, 3);
    assertEquals(175.0, calories, 0.1);
  }

  @Test
  void testCalculateFastWalking() {
    WalkingCaloriesCalculator calculator = new WalkingCaloriesCalculator(70);
    double calories = calculator.calculate(30, 4);
    assertEquals(175.0, calories, 0.1);
  }
}
