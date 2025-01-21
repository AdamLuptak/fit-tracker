package io.rebase.fitnessapp.activity.domain.calculators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesCalculator;
import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesModifier;
import io.rebase.fitnessapp.calorie.domain.calculators.ModifiedCaloriesCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModifiedCaloriesCalculatorTest {

  private CaloriesCalculator baseCalculator;
  private ModifiedCaloriesCalculator modifiedCalorieCalculator;

  @BeforeEach
  void setUp() {
    baseCalculator = mock(CaloriesCalculator.class);
    modifiedCalorieCalculator = new ModifiedCaloriesCalculator(baseCalculator);
  }

  @Test
  void testCalculateWithoutModifiers() {
    when(baseCalculator.calculate(30, 5)).thenReturn(300.0);

    double result = modifiedCalorieCalculator.calculate(30, 5);

    assertEquals(300.0, result);
  }

  @Test
  void testCalculateWithModifiers() {
    when(baseCalculator.calculate(30, 5)).thenReturn(300.0);
    var modifier = mock(CaloriesModifier.class);
    when(modifier.modify(300.0, 30, 5)).thenReturn(350.0);

    modifiedCalorieCalculator.addModifier(modifier);
    double result = modifiedCalorieCalculator.calculate(30, 5);

    assertEquals(350.0, result);
  }
}
