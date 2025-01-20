package io.codething.sporttracker.domain.factories;

import io.codething.sporttracker.domain.calculators.CaloriesCalculator;
import io.codething.sporttracker.domain.calculators.RunningCaloriesCalculator;
import io.codething.sporttracker.domain.calculators.SwimmingCaloriesCalculator;
import io.codething.sporttracker.domain.calculators.WalkingCaloriesCalculator;
import io.codething.sporttracker.domain.User;
import io.codething.sporttracker.domain.ActivityType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ActivityTypeFactory {
    public static final Map<ActivityType.ActivityName, Function<Double, CaloriesCalculator>> calculatorFactoryMap = new HashMap<>();

    static {
        calculatorFactoryMap.put(ActivityType.ActivityName.RUNNING, RunningCaloriesCalculator::new);
        calculatorFactoryMap.put(ActivityType.ActivityName.SWIMMING, SwimmingCaloriesCalculator::new);
        calculatorFactoryMap.put(ActivityType.ActivityName.WALKING, WalkingCaloriesCalculator::new);
    }

    public ActivityType create(User user, ActivityType.ActivityName activityName) {
        Function<Double, CaloriesCalculator> factory = calculatorFactoryMap.get(activityName);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported activity activityName: " + activityName);
        }
        return new ActivityType(activityName, factory.apply(user.weight()));
    }
}
