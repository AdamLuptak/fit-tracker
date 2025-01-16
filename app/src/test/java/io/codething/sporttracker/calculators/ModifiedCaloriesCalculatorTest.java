package io.codething.sporttracker.calculators;

import io.codething.sporttracker.domain.calculators.CaloriesCalculator;
import io.codething.sporttracker.domain.calculators.CaloriesModifier;
import io.codething.sporttracker.domain.calculators.ModifiedCaloriesCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ModifiedCaloriesCalculatorTest {

    private CaloriesCalculator baseCalculator;
    private ModifiedCaloriesCalculator modifiedCalorieCalculator;

    @BeforeEach
    void setUp() {
        baseCalculator = mock(CaloriesCalculator.class);
        modifiedCalorieCalculator = new ModifiedCaloriesCalculator(baseCalculator);
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
        var modifier = mock(CaloriesModifier.class);
        when(modifier.modify(300.0, 30, 5)).thenReturn(350.0);

        modifiedCalorieCalculator.addModifier(modifier);
        double result = modifiedCalorieCalculator.calculateCalories(30, 5);

        assertEquals(350.0, result);
    }
}