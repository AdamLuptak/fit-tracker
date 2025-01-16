package io.codething.sporttracker.application.services;

import io.codething.sporttracker.domain.entities.Activity;
import io.codething.sporttracker.domain.exceptions.UserNotFoundException;
import io.codething.sporttracker.domain.factories.ActivityFactory;
import io.codething.sporttracker.domain.factories.ActivityTypeFactory;
import io.codething.sporttracker.domain.valueobjects.ActivityType;
import io.codething.sporttracker.domain.valueobjects.ActivityType.ActivityName;
import io.codething.sporttracker.infrastructure.repositories.ActivityRepository;
import io.codething.sporttracker.infrastructure.repositories.UserRepository;
import io.codething.sporttracker.shared.IdGenerator;

import java.time.Duration;
import java.util.List;

public class ActivityTrackerService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final UserService userService;
    private final ActivityFactory activityFactory;
    private final ActivityTypeFactory activityTypeFactory;

    private final IdGenerator idGenerator;

    public ActivityTrackerService(UserRepository userRepository, ActivityRepository activityRepository, UserService userService, ActivityFactory activityFactory, ActivityTypeFactory activityTypeFactory, IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.userService = userService;
        this.activityFactory = activityFactory;
        this.activityTypeFactory = activityTypeFactory;
        this.idGenerator = idGenerator;
    }

    public void addActivity(String userId, ActivityName activityName, Duration duration, double distance) {
        userRepository.findById(userId)
                .ifPresentOrElse(user -> {
                    ActivityType activityType = activityTypeFactory.create(user, activityName);

                    var caloriesModifiers = userService.getCaloriesModifiers(userId, activityType.name());
                    var activity = activityFactory.create(
                            user,
                            activityType,
                            duration,
                            distance,
                            caloriesModifiers
                    );
                    activityRepository.save(activity);
                }, () -> {
                    throw new UserNotFoundException(userId);
                });
    }

    public List<Activity> getUserActivities(String userId) {
        return activityRepository.findByUserId(userId);
    }
}
