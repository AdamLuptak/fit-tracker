package io.codething.sporttracker.application.services;

import io.codething.sporttracker.Application;
import io.codething.sporttracker.infrastructure.repositories.ActivityRepository;
import io.codething.sporttracker.infrastructure.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityReportingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public ActivityReportingService(UserRepository userRepository, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    public void printAll() {
        var users = userRepository.findAll();
        if (users.isEmpty()) {
            LOGGER.info("No users found.");
            return;
        }

        for (var user : users) {
            var activities = activityRepository.findByUserId(user.id());
            LOGGER.info("User: {}, Age: {}, Weight: {}", user.name(), user.age(), user.weight());
            if (activities.isEmpty()) {
                LOGGER.info("No activities found for user: {}", user.id());
            } else {
                for (var activity : activities) {
                    LOGGER.info("Activity: {}, Duration: {}, Distance: {}, Calories: {}", activity.type(), activity.duration(), activity.distance(), activity.calories());
                }
            }
        }
    }

}
