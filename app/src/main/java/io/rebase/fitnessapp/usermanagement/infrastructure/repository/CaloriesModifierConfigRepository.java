package io.rebase.fitnessapp.usermanagement.infrastructure.repository;

import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesModifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaloriesModifierConfigRepository {

  private final Map<String, List<CaloriesModifier>> configs;

  public CaloriesModifierConfigRepository() {
    this.configs = new HashMap<>();
  }

  public void save(String userId, String activityName, List<CaloriesModifier> caloriesModifiers) {
    configs.put(userId + "_" + activityName, caloriesModifiers);
  }

  public List<CaloriesModifier> findAllByUserIdAndActivityName(String userId, String activityName) {
    return configs.getOrDefault(userId + "_" + activityName, Collections.emptyList());
  }
}
