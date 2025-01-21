package io.rebase.fitnessapp.activity.domain.calculators;

import static org.junit.jupiter.api.Assertions.*;

import io.rebase.fitnessapp.calorie.domain.calculators.SwimmingCaloriesCalculator;
import org.junit.jupiter.api.Test;

class SwimmingCaloriesCalculatorTest {

  @Test
  void testCalculate() {
    SwimmingCaloriesCalculator calculator = new SwimmingCaloriesCalculator(70); // weight in kg
    double calories = calculator.calculate(30, 1); // 30 minutes, 1 km
    assertEquals(280, calories, 0.1); // Expected calories burned
  }

  @Test
  void testCalculateLightSwimming() {
    SwimmingCaloriesCalculator calculator = new SwimmingCaloriesCalculator(70);
    double calories = calculator.calculate(30, 0.5); // 30 minutes, 0.5 km
    assertEquals(210, calories, 0.1);
  }

  @Test
  void testCalculateModerateSwimming() {
    SwimmingCaloriesCalculator calculator = new SwimmingCaloriesCalculator(70);
    double calories = calculator.calculate(30, 1.5); // 30 minutes, 1.5 km
    assertEquals(350, calories, 0.1);
  }
}
