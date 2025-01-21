package io.rebase.fitnessapp.usermanagement.application.service;

import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesModifier;
import io.rebase.fitnessapp.usermanagement.infrastructure.repository.CaloriesModifierConfigRepository;
import java.util.List;

public class UserConfigService {
  private final CaloriesModifierConfigRepository caloriesModifierConfigRepository;

  public UserConfigService(CaloriesModifierConfigRepository caloriesModifierConfigRepository) {
    this.caloriesModifierConfigRepository = caloriesModifierConfigRepository;
  }

  public void save(String userId, String activityName, List<CaloriesModifier> caloriesModifiers) {
    caloriesModifierConfigRepository.save(userId, activityName, caloriesModifiers);
  }

  public List<CaloriesModifier> findAllByUserIdAndActivityName(String userId, String activityName) {
    return caloriesModifierConfigRepository.findAllByUserIdAndActivityName(userId, activityName);
  }
}
