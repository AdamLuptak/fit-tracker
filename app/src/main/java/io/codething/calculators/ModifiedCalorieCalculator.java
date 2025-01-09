package io.codething.calculators;

import java.util.ArrayList;
import java.util.List;

public class ModifiedCalorieCalculator implements CalorieCalculator {
    private CalorieCalculator baseCalculator;
    private List<CalorieModifier> modifiers;

    public ModifiedCalorieCalculator(CalorieCalculator baseCalculator) {
        this.baseCalculator = baseCalculator;
        this.modifiers = new ArrayList<>();
    }

    public void addModifier(CalorieModifier modifier) {
        modifiers.add(modifier);
    }

    @Override
    public double calculateCalories(double duration, double distance) {
        var baseCalories = baseCalculator.calculateCalories(duration, distance);
        for (CalorieModifier modifier : modifiers) {
            baseCalories = modifier.modify(baseCalories, duration, distance);
        }
        return baseCalories;
    }
}
