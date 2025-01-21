package io.rebase.fitnessapp.calorie.domain.calculators;

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
  public double calculate(double duration, double distance) {
    var baseCalories = baseCalculator.calculate(duration, distance);
    for (CaloriesModifier modifier : modifiers) {
      baseCalories = modifier.modify(baseCalories, duration, distance);
    }
    return baseCalories;
  }
}
