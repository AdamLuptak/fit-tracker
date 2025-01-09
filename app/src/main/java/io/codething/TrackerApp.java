package io.codething;

import io.codething.calculators.CalorieModifier;
import io.codething.calculators.ModifiedCalorieCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class TrackerApp {
    private final Logger LOGGER = LoggerFactory.getLogger(TrackerApp.class);
    private final UserService userService;
    public TrackerApp(UserService userService) {
        this.userService = userService;
    }

    public void addActivity(String userName, Activity.ActivityType type, double duration, double distance, List<CalorieModifier> modifiers) {
        var user = userService.findUser(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userName);
        }

        var calculator = ActivityFactory.createBaseCalculator(user, type);

        if (modifiers != null && !modifiers.isEmpty()) {
            var modifiedCalculator = new ModifiedCalorieCalculator(calculator);
            for (CalorieModifier modifier : modifiers) {
                modifiedCalculator.addModifier(modifier);
            }
            calculator = modifiedCalculator;
        }

        var activity = new Activity(type, duration, distance, calculator.calculateCalories(duration, distance));
        user.addActivity(activity);
    }

    public void printUserSummary(String userName) {
        var user = userService.findUser(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userName);
        }

        LOGGER.info("Summary for User: {}", user.name());
        for (Activity activity : user.activities()) {
            LOGGER.info(activity.toString());
        }
        LOGGER.info(user.toString());
    }
}
