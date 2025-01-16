package io.codething.sporttracker.calculators;

import io.codething.sporttracker.domain.calculators.WalkingCaloriesCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalkingCaloriesCalculatorTest {
    @Test
    void testCalculateCaloriesSlowWalking() {
        WalkingCaloriesCalculator calculator = new WalkingCaloriesCalculator(70);
        double calories = calculator.calculateCalories(30, 2);
        assertEquals(122.5, calories, 0.1);
    }

    @Test
    void testCalculateCaloriesModerateWalking() {
        WalkingCaloriesCalculator calculator = new WalkingCaloriesCalculator(70);
        double calories = calculator.calculateCalories(30, 3);
        assertEquals(175.0, calories, 0.1);
    }

    @Test
    void testCalculateCaloriesFastWalking() {
        WalkingCaloriesCalculator calculator = new WalkingCaloriesCalculator(70);
        double calories = calculator.calculateCalories(30, 4);
        assertEquals(175.0, calories, 0.1);
    }
}