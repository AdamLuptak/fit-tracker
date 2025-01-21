package io.rebase.fitnessapp.activity.domain.model;

import java.util.List;

public record CalorieProjection(
    Activity activity,
    String userId,
    double baseCalories,
    double modifiedCalories,
    List<String> calorieModifiers) {}
