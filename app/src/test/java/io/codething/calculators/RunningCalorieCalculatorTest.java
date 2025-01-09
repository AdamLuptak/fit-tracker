package io.codething.calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RunningCalorieCalculatorTest {

    @Test
    void shouldCalculateCalories() {
        var calculator = new RunningCalorieCalculator(70); // weight in kg

        var caloriesBurned = calculator.calculateCalories(30, 5); // 30 minutes, 5 km
        assertEquals(420, caloriesBurned, 0.1);

        caloriesBurned = calculator.calculateCalories(60, 10); // 60 minutes, 10 km
        assertEquals(840, caloriesBurned, 0.1);

        caloriesBurned = calculator.calculateCalories(45, 7); // 45 minutes, 7 km
        assertEquals(472.5, caloriesBurned, 0.1);
    }
}