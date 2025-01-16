package io.codething.sporttracker.domain.calculators;

import java.util.ArrayList;
import java.util.List;

public class ModifiedCaloriesCalculator implements CaloriesCalculator {
    private CaloriesCalculator baseCalculator;
    private List<CaloriesModifier> modifiers;

    public ModifiedCaloriesCalculator(CaloriesCalculator baseCalculator) {
        this.baseCalculator = baseCalculator;
        this.modifiers = new ArrayList<>();
    }

    public void addModifier(CaloriesModifier modifier) {
        modifiers.add(modifier);
    }

    @Override
    public double calculateCalories(double duration, double distance) {
        var baseCalories = baseCalculator.calculateCalories(duration, distance);
        for (CaloriesModifier modifier : modifiers) {
            baseCalories = modifier.modify(baseCalories, duration, distance);
        }
        return baseCalories;
    }
}
