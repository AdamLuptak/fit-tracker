package io.codething.sporttracker.calculators;

import static org.junit.jupiter.api.Assertions.*;

import io.codething.sporttracker.domain.calculators.SwimmingCaloriesCalculator;
import org.junit.jupiter.api.Test;

class SwimmingCaloriesCalculatorTest {

    @Test
    void testCalculateCalories() {
        SwimmingCaloriesCalculator calculator = new SwimmingCaloriesCalculator(70); // weight in kg
        double calories = calculator.calculateCalories(30, 1); // 30 minutes, 1 km
        assertEquals(280, calories, 0.1); // Expected calories burned
    }

    @Test
    void testCalculateCaloriesLightSwimming() {
        SwimmingCaloriesCalculator calculator = new SwimmingCaloriesCalculator(70);
        double calories = calculator.calculateCalories(30, 0.5); // 30 minutes, 0.5 km
        assertEquals(210, calories, 0.1);
    }

    @Test
    void testCalculateCaloriesModerateSwimming() {
        SwimmingCaloriesCalculator calculator = new SwimmingCaloriesCalculator(70);
        double calories = calculator.calculateCalories(30, 1.5); // 30 minutes, 1.5 km
        assertEquals(350, calories, 0.1);
    }
}

