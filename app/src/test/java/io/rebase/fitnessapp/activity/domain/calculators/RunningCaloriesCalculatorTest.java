package io.rebase.fitnessapp.activity.domain.calculators;

import static org.junit.jupiter.api.Assertions.*;

import io.rebase.fitnessapp.calorie.domain.calculators.RunningCaloriesCalculator;
import org.junit.jupiter.api.Test;

class RunningCaloriesCalculatorTest {

  @Test
  void shouldCalculate() {
    var calculator = new RunningCaloriesCalculator(70); // weight in kg

    var caloriesBurned = calculator.calculate(30, 5); // 30 minutes, 5 km
    assertEquals(420, caloriesBurned, 0.1);

    caloriesBurned = calculator.calculate(60, 10); // 60 minutes, 10 km
    assertEquals(840, caloriesBurned, 0.1);

    caloriesBurned = calculator.calculate(45, 7); // 45 minutes, 7 km
    assertEquals(472.5, caloriesBurned, 0.1);
  }
}
