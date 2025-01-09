package io.codething.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalkingCalorieCalculatorTest {
    @Test
    void testCalculateCaloriesSlowWalking() {
        WalkingCalorieCalculator calculator = new WalkingCalorieCalculator(70);
        double calories = calculator.calculateCalories(30, 2);
        assertEquals(122.5, calories, 0.1);
    }

    @Test
    void testCalculateCaloriesModerateWalking() {
        WalkingCalorieCalculator calculator = new WalkingCalorieCalculator(70);
        double calories = calculator.calculateCalories(30, 3);
        assertEquals(175.0, calories, 0.1);
    }

    @Test
    void testCalculateCaloriesFastWalking() {
        WalkingCalorieCalculator calculator = new WalkingCalorieCalculator(70);
        double calories = calculator.calculateCalories(30, 4);
        assertEquals(175.0, calories, 0.1);
    }
}