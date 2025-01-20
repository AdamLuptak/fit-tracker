package io.codething.sporttracker.application.services;

import io.codething.sporttracker.domain.calculators.CaloriesModifier;
import io.codething.sporttracker.domain.ActivityType.ActivityName;
import io.codething.sporttracker.infrastructure.repositories.CaloriesModifierConfigRepository;

import java.util.List;

public class UserService {

    private final CaloriesModifierConfigRepository calorieModifierConfigRepository;

    public UserService(CaloriesModifierConfigRepository calorieModifierConfigRepository) {
        this.calorieModifierConfigRepository = calorieModifierConfigRepository;
    }

    public void upsertCaloriesModifiers(String userId, ActivityName activityName, List<CaloriesModifier> caloriesModifiers) {
        calorieModifierConfigRepository.save(userId, activityName, caloriesModifiers);
    }

    public List<CaloriesModifier> getCaloriesModifiers(String userId, ActivityName activityName) {
        return calorieModifierConfigRepository.findAllByUserIdAndActivityType(userId, activityName);
    }

}
