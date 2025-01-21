package io.rebase.fitnessapp.stats.application.query;

import io.rebase.fitnessapp.activity.infrastructure.repositories.CalorieProjectionRepository;
import io.rebase.fitnessapp.shared.exceptions.ActivityNotFoundException;
import io.rebase.fitnessapp.stats.domain.model.UserActivityReport;

public class UserActivityReportUserCase {
  private final CalorieProjectionRepository calorieProjectionRepository;

  public UserActivityReportUserCase(CalorieProjectionRepository calorieProjectionRepository) {
    this.calorieProjectionRepository = calorieProjectionRepository;
  }

  public UserActivityReport handle(UserActivityReportQuery query) {
    return calorieProjectionRepository
        .findByUserIdAndActivityId(query.userId(), query.activityId())
        .map(
            projection ->
                new UserActivityReport(
                    projection.userId(),
                    projection.activity().type(),
                    projection.activity().distance(),
                    projection.activity().duration(),
                    projection.baseCalories(),
                    projection.modifiedCalories(),
                    projection.calorieModifiers()))
        .orElseThrow(() -> new ActivityNotFoundException((query.userId())));
  }
}
