package io.codething.sporttracker;

import io.codething.sporttracker.application.services.UserService;
import io.codething.sporttracker.application.services.ActivityReportingService;
import io.codething.sporttracker.application.services.ActivityTrackerService;
import io.codething.sporttracker.domain.calculators.BMRCalculator;
import io.codething.sporttracker.domain.calculators.BMRCaloriesModifier;
import io.codething.sporttracker.domain.calculators.CaloriesModifier;
import io.codething.sporttracker.domain.User;
import io.codething.sporttracker.domain.factories.ActivityFactory;
import io.codething.sporttracker.domain.factories.ActivityTypeFactory;
import io.codething.sporttracker.infrastructure.repositories.ActivityRepository;
import io.codething.sporttracker.infrastructure.repositories.CaloriesModifierConfigRepository;
import io.codething.sporttracker.infrastructure.repositories.UserRepository;
import io.codething.sporttracker.shared.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static io.codething.sporttracker.domain.User.Gender.FEMALE;
import static io.codething.sporttracker.domain.User.Gender.MALE;
import static io.codething.sporttracker.domain.ActivityType.ActivityName.RUNNING;
import static io.codething.sporttracker.domain.ActivityType.ActivityName.SWIMMING;
import static io.codething.sporttracker.domain.ActivityType.ActivityName.WALKING;


public class Application {
    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            // initialization
            var idGenerator = new IdGenerator();
            var bmrCalculator = new BMRCalculator();
            var userRepository = new UserRepository();
            var userConfigService = new UserService(new CaloriesModifierConfigRepository());
            var activityRepository = new ActivityRepository();
            var activityTrackerService = getActivityTrackerService(idGenerator, userRepository, activityRepository, userConfigService);
            var activityReportingService = new ActivityReportingService(userRepository, activityRepository);

            // data load
            var alice = new User(idGenerator.generate(), FEMALE, "Alice", 21, 150, 50); // BMR = 1500 calories/day
            var bob = new User(idGenerator.generate(), MALE, "Bob", 22, 180, 90);   // BMR = 1800 calories/day
            userRepository.save(alice);
            userRepository.save(bob);

            List<CaloriesModifier> defaultModifiers = List.of(new BMRCaloriesModifier(bmrCalculator.calculate(alice)));
            userConfigService.upsertCaloriesModifiers(alice.id(),WALKING, defaultModifiers);
            userConfigService.upsertCaloriesModifiers(bob.id(),RUNNING, defaultModifiers);

            // activity tracking
            activityTrackerService.addActivity(alice.id(), WALKING,  Duration.ofMinutes(30), 5);
            activityTrackerService.addActivity(alice.id(), SWIMMING,  Duration.ofMinutes(15), 5);
            activityTrackerService.addActivity(bob.id(), RUNNING,  Duration.ofMinutes(15), 5);

            // reporting
            activityReportingService.printAll();
            LOGGER.info("----");
            // changing config
            userConfigService.upsertCaloriesModifiers(alice.id(),WALKING, Collections.emptyList());
            activityTrackerService.addActivity(alice.id(), WALKING,  Duration.ofMinutes(30), 5);
            activityReportingService.printAll();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static ActivityTrackerService getActivityTrackerService(IdGenerator idGenerator, UserRepository userRepository, ActivityRepository activityRepository, UserService userService) {
        var activityFactory = new ActivityFactory(idGenerator);
        var activityTypeFactory = new ActivityTypeFactory();

        return new ActivityTrackerService(userRepository, activityRepository, userService, activityFactory, activityTypeFactory, idGenerator);
    }
}
