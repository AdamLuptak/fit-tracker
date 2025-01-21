package io.rebase.fitnessapp;

import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.RUNNING;
import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.SWIMING;
import static io.rebase.fitnessapp.activity.domain.model.SupportedActivityType.WALKING;
import static io.rebase.fitnessapp.usermanagement.domain.model.User.Gender.FEMALE;
import static io.rebase.fitnessapp.usermanagement.domain.model.User.Gender.MALE;

import io.rebase.fitnessapp.activity.application.command.CreateActivityCommand;
import io.rebase.fitnessapp.activity.application.command.CreateActivityUseCase;
import io.rebase.fitnessapp.activity.application.event.ActivityEventListener;
import io.rebase.fitnessapp.activity.application.services.ActivityTrackerService;
import io.rebase.fitnessapp.activity.application.validation.CreateActivityCommandValidator;
import io.rebase.fitnessapp.activity.domain.event.ActivityCreatedEvent;
import io.rebase.fitnessapp.activity.domain.model.Distance;
import io.rebase.fitnessapp.activity.infrastructure.repositories.ActivityRepository;
import io.rebase.fitnessapp.activity.infrastructure.repositories.CalorieProjectionRepository;
import io.rebase.fitnessapp.calorie.domain.calculators.BMRCalculator;
import io.rebase.fitnessapp.calorie.domain.calculators.BMRCaloriesModifier;
import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesCalculatorFactory;
import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesModifier;
import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesModifierFactory;
import io.rebase.fitnessapp.shared.IdGenerator;
import io.rebase.fitnessapp.shared.eventstore.InMemoryEventStore;
import io.rebase.fitnessapp.stats.application.query.UserActivitiesReportQuery;
import io.rebase.fitnessapp.stats.application.query.UserActivitiesReportUserCase;
import io.rebase.fitnessapp.stats.application.query.UserActivityReportUserCase;
import io.rebase.fitnessapp.stats.application.service.ActivityReportPrinter;
import io.rebase.fitnessapp.stats.application.service.ActivityStatsService;
import io.rebase.fitnessapp.usermanagement.application.service.UserConfigService;
import io.rebase.fitnessapp.usermanagement.application.service.UserService;
import io.rebase.fitnessapp.usermanagement.domain.model.User;
import io.rebase.fitnessapp.usermanagement.infrastructure.repository.CaloriesModifierConfigRepository;
import io.rebase.fitnessapp.usermanagement.infrastructure.repository.UserRepository;
import java.time.Clock;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    try {
      // initialization
      var idGenerator = new IdGenerator();
      var bmrCalculator = new BMRCalculator();
      var userRepository = new UserRepository();
      var userService = new UserService(userRepository);
      var caloriesModifierConfigRepository = new CaloriesModifierConfigRepository();
      var userConfigService = new UserConfigService(caloriesModifierConfigRepository);
      var activityRepository = new ActivityRepository();
      var eventStore = new InMemoryEventStore();
      var clock = Clock.systemUTC();
      var calorieProjectionRepository = new CalorieProjectionRepository();
      var caloriesCalculatorFactory = new CaloriesCalculatorFactory();
      var caloriesModifierFactory = new CaloriesModifierFactory();
      var createActivityUseCase =
          new CreateActivityUseCase(activityRepository, eventStore, idGenerator, clock);
      var activityEventListener =
          new ActivityEventListener(
              calorieProjectionRepository,
              caloriesCalculatorFactory,
              caloriesModifierFactory,
              userConfigService,
              userService);
      eventStore.registerListener(ActivityCreatedEvent.class, activityEventListener);
      var createActivityCommandValidator = new CreateActivityCommandValidator(userService);
      var activityTrackerService =
          new ActivityTrackerService(createActivityUseCase, createActivityCommandValidator);

      var userActivityReportUserCase = new UserActivityReportUserCase(calorieProjectionRepository);
      var userActivitiesReportUserCase =
          new UserActivitiesReportUserCase(calorieProjectionRepository);
      var activityStatsService =
          new ActivityStatsService(userActivityReportUserCase, userActivitiesReportUserCase);
      var activityReportPrinter = new ActivityReportPrinter();

      // data load
      var alice =
          new User(idGenerator.generate(), FEMALE, "Alice", 21, 150, 50); // BMR = 1500 calories/day
      var bob =
          new User(idGenerator.generate(), MALE, "Bob", 22, 180, 90); // BMR = 1800 calories/day
      userRepository.save(alice);
      userRepository.save(bob);

      List<CaloriesModifier> defaultModifiers =
          List.of(new BMRCaloriesModifier(bmrCalculator.calculate(alice)));
      userConfigService.save(alice.id(), WALKING, defaultModifiers);
      userConfigService.save(bob.id(), RUNNING, defaultModifiers);

      // activity tracking
      activityTrackerService.addActivity(
          new CreateActivityCommand(alice.id(), WALKING, Duration.ofMinutes(30), Distance.of(5d)));
      activityTrackerService.addActivity(
          new CreateActivityCommand(alice.id(), SWIMING, Duration.ofMinutes(15), Distance.of(5d)));
      activityTrackerService.addActivity(
          new CreateActivityCommand(bob.id(), RUNNING, Duration.ofMinutes(15), Distance.of(5d)));

      // reporting
      activityReportPrinter.printAllActivities(
          alice,
          activityStatsService.getUserActivitiesReport(new UserActivitiesReportQuery(alice.id())));
      activityReportPrinter.printAllActivities(
          alice,
          activityStatsService.getUserActivitiesReport(new UserActivitiesReportQuery(bob.id())));

      LOGGER.info("----");
      // changing config
      userConfigService.save(alice.id(), WALKING, Collections.emptyList());
      activityTrackerService.addActivity(
          new CreateActivityCommand(alice.id(), WALKING, Duration.ofMinutes(30), Distance.of(5d)));
      activityReportPrinter.printAllActivities(
          alice,
          activityStatsService.getUserActivitiesReport(new UserActivitiesReportQuery(alice.id())));

    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }
}
