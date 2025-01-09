package io.codething;

import io.codething.calculators.CalorieCalculator;
import io.codething.calculators.CalorieModifier;
import io.codething.calculators.RunningCalorieCalculator;
import io.codething.calculators.SwimmingCalorieCalculator;
import io.codething.calculators.WalkingCalorieCalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


class ActivityFactory {

    public static final Map<Activity.ActivityType, Function<Double, CalorieCalculator>> calculatorFactoryMap = new HashMap<>();
    public static final Map<Class<? extends CalorieModifier>, Function<User, CalorieModifier>> calorieModifierFactoryMap = new HashMap<>();

    static {
        calculatorFactoryMap.put(Activity.ActivityType.RUNNING, RunningCalorieCalculator::new);
        calculatorFactoryMap.put(Activity.ActivityType.SWIMMING, SwimmingCalorieCalculator::new);
        calculatorFactoryMap.put(Activity.ActivityType.WALKING, WalkingCalorieCalculator::new);
    }

    public static CalorieCalculator createBaseCalculator(User user, Activity.ActivityType type) {
        Function<Double, CalorieCalculator> factory = calculatorFactoryMap.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported activity type: " + type);
        }
        return factory.apply(user.weight());
    }
}