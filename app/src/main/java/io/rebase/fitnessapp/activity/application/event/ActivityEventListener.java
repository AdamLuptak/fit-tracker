package io.rebase.fitnessapp.activity.application.event;

import io.rebase.fitnessapp.activity.domain.event.ActivityCreatedEvent;
import io.rebase.fitnessapp.activity.domain.model.Activity;
import io.rebase.fitnessapp.activity.domain.model.CalorieProjection;
import io.rebase.fitnessapp.activity.infrastructure.messaging.Consumer;
import io.rebase.fitnessapp.activity.infrastructure.repositories.CalorieProjectionRepository;
import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesCalculatorFactory;
import io.rebase.fitnessapp.calorie.domain.calculators.CaloriesModifierFactory;
import io.rebase.fitnessapp.usermanagement.application.service.UserConfigService;
import io.rebase.fitnessapp.usermanagement.application.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityEventListener implements Consumer<ActivityCreatedEvent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(ActivityEventListener.class);
  private final CalorieProjectionRepository calorieProjectionRepository;
  private final CaloriesCalculatorFactory caloriesCalculatorFactory;
  private final CaloriesModifierFactory caloriesModifierFactory;
  private final UserConfigService userConfigService;
  private final UserService userService;

  public ActivityEventListener(
      CalorieProjectionRepository calorieProjectionRepository,
      CaloriesCalculatorFactory caloriesCalculatorFactory,
      CaloriesModifierFactory caloriesModifierFactory,
      UserConfigService userConfigService,
      UserService userService) {
    this.calorieProjectionRepository = calorieProjectionRepository;
    this.caloriesCalculatorFactory = caloriesCalculatorFactory;
    this.caloriesModifierFactory = caloriesModifierFactory;
    this.userConfigService = userConfigService;
    this.userService = userService;
  }

  @Override
  public void accept(ActivityCreatedEvent event) {
    LOGGER.info("Received event: {}", event);
    var user = userService.findById(event.userId()).orElseThrow();
    var calorieModifiers =
        userConfigService.findAllByUserIdAndActivityName(event.userId(), event.activityType());

    var calculator = caloriesCalculatorFactory.create(user, event.activityType());

    var baseCalories = calculator.calculate(event.duration().toMinutes(), event.distance().value());
    var finalCalories =
        calorieModifiers.stream()
            .reduce(
                baseCalories,
                (calories, modifier) ->
                    modifier.modify(
                        calories, event.duration().toMinutes(), event.distance().value()),
                (a, b) -> a);

    var calorieModifiersSnapshot =
        calorieModifiers.stream().map(Object::getClass).map(Class::getSimpleName).toList();
    var projection =
        new CalorieProjection(
            new Activity(
                event.activityId(),
                event.userId(),
                event.activityType(),
                event.duration(),
                event.distance()),
            event.userId(),
            baseCalories,
            finalCalories,
            calorieModifiersSnapshot);
    calorieProjectionRepository.save(projection);

    LOGGER.info("Calorie projection saved: {}", projection);
  }
}
