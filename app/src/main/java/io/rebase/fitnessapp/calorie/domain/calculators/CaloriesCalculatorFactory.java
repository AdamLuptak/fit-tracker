package io.rebase.fitnessapp.calorie.domain.calculators;

import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.RUNNING;
import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.SWIMING;
import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.WALKING;

import io.rebase.fitnessapp.usermanagement.domain.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CaloriesCalculatorFactory {
  public static final Map<String, Function<Double, CaloriesCalculator>> calculatorFactoryMap =
      new HashMap<>();

  static {
    calculatorFactoryMap.put(RUNNING, RunningCaloriesCalculator::new);
    calculatorFactoryMap.put(SWIMING, SwimmingCaloriesCalculator::new);
    calculatorFactoryMap.put(WALKING, WalkingCaloriesCalculator::new);
  }

  public CaloriesCalculator create(User user, String type) {
    Function<Double, CaloriesCalculator> factory = calculatorFactoryMap.get(type);
    if (factory == null) {
      throw new IllegalArgumentException("Unsupported calories calculator type: " + type);
    }
    return factory.apply(user.weight());
  }
}
