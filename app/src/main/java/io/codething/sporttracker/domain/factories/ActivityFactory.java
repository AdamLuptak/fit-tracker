package io.codething.sporttracker.domain.factories;

import io.codething.sporttracker.domain.calculators.CaloriesModifier;
import io.codething.sporttracker.domain.Activity;
import io.codething.sporttracker.domain.User;
import io.codething.sporttracker.domain.ActivityType;
import io.codething.sporttracker.shared.IdGenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


public class ActivityFactory {


    private final IdGenerator idGenerator;

    public ActivityFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Activity create(User user, ActivityType type, Duration duration, double distance, List<CaloriesModifier> caloriesModifiers) {
        String id = idGenerator.generate();
        LocalDateTime date = LocalDateTime.now();
        var baseCalories = type.calculateCalories(duration, distance);
        var finalCalories = caloriesModifiers.stream()
                .reduce(baseCalories, (calories,
                                       modifier) -> modifier.modify(calories, duration.toMinutes(), distance),
                        (a, b) -> a);

        return new Activity(id, user.id(), type.name(), duration, date, finalCalories, distance);
    }

}