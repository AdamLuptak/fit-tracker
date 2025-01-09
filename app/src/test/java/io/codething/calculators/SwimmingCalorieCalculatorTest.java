package io.codething.calculators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwimmingCalorieCalculatorTest {

    @Test
    void testCalculateCalories() {
        SwimmingCalorieCalculator calculator = new SwimmingCalorieCalculator(70); // weight in kg
        double calories = calculator.calculateCalories(30, 1); // 30 minutes, 1 km
        assertEquals(280, calories, 0.1); // Expected calories burned
    }

    @Test
    void testCalculateCaloriesLightSwimming() {
        SwimmingCalorieCalculator calculator = new SwimmingCalorieCalculator(70);
        double calories = calculator.calculateCalories(30, 0.5); // 30 minutes, 0.5 km
        assertEquals(210, calories, 0.1);
    }

    @Test
    void testCalculateCaloriesModerateSwimming() {
        SwimmingCalorieCalculator calculator = new SwimmingCalorieCalculator(70);
        double calories = calculator.calculateCalories(30, 1.5); // 30 minutes, 1.5 km
        assertEquals(350, calories, 0.1);
    }
}

