package io.codething.calculators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ModifiedCalorieCalculatorTest {

    private CalorieCalculator baseCalculator;
    private ModifiedCalorieCalculator modifiedCalorieCalculator;

    @BeforeEach
    void setUp() {
        baseCalculator = mock(CalorieCalculator.class);
        modifiedCalorieCalculator = new ModifiedCalorieCalculator(baseCalculator);
    }

    @Test
    void testCalculateCaloriesWithoutModifiers() {
        when(baseCalculator.calculateCalories(30, 5)).thenReturn(300.0);

        double result = modifiedCalorieCalculator.calculateCalories(30, 5);

        assertEquals(300.0, result);
    }

    @Test
    void testCalculateCaloriesWithModifiers() {
        when(baseCalculator.calculateCalories(30, 5)).thenReturn(300.0);
        var modifier = mock(CalorieModifier.class);
        when(modifier.modify(300.0, 30, 5)).thenReturn(350.0);

        modifiedCalorieCalculator.addModifier(modifier);
        double result = modifiedCalorieCalculator.calculateCalories(30, 5);

        assertEquals(350.0, result);
    }
}