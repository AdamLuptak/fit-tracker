package io.codething.sporttracker.infrastructure.repositories;

import io.codething.sporttracker.domain.calculators.CaloriesModifier;
import io.codething.sporttracker.domain.valueobjects.ActivityType.ActivityName;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaloriesModifierConfigRepository {

    private final Map<String, List<CaloriesModifier>> configs;

    public CaloriesModifierConfigRepository() {
        this.configs = new HashMap<>();
    }

    public void save(String userId, ActivityName activityName, List<CaloriesModifier> caloriesModifiers) {
        configs.put(userId + "_" + activityName, caloriesModifiers);
    }

    public List<CaloriesModifier> findAllByUserIdAndActivityType(String userId, ActivityName activityName) {
        return configs.getOrDefault(userId + "_" + activityName, Collections.emptyList());
    }
}
