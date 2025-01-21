package io.rebase.fitnessapp.calorie.domain.calculators;

import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.RUNNING;
import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.SWIMING;
import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.WALKING;

import io.rebase.fitnessapp.usermanagement.domain.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CaloriesModifierFactory {
  public static final Map<String, List<Function<User, CaloriesModifier>>> calculatorFactoryMap =
      new HashMap<>();

  static {
    calculatorFactoryMap.put(RUNNING, List.of((user) -> new BMRCaloriesModifier(user.bmr())));
    calculatorFactoryMap.put(SWIMING, List.of((user) -> new BMRCaloriesModifier(user.bmr())));
    calculatorFactoryMap.put(WALKING, List.of((user) -> new BMRCaloriesModifier(user.bmr())));
  }

  public List<CaloriesModifier> create(User user, String type) {
    var factoryList = calculatorFactoryMap.get(type);
    if (factoryList == null) {
      throw new IllegalArgumentException("Unsupported calories calculator type: " + type);
    }
    return factoryList.stream().map(f -> f.apply(user)).toList();
  }
}
