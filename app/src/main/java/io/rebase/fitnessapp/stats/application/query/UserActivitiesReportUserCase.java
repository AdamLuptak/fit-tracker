package io.rebase.fitnessapp.stats.application.query;

import io.rebase.fitnessapp.activity.infrastructure.repositories.CalorieProjectionRepository;
import io.rebase.fitnessapp.stats.domain.model.UserActivityReport;
import java.util.List;

public class UserActivitiesReportUserCase {
  private final CalorieProjectionRepository calorieProjectionRepository;

  public UserActivitiesReportUserCase(CalorieProjectionRepository calorieProjectionRepository) {
    this.calorieProjectionRepository = calorieProjectionRepository;
  }

  public List<UserActivityReport> handle(UserActivitiesReportQuery query) {
    return calorieProjectionRepository.findAllByUserId(query.userId()).stream()
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
        .toList();
  }
}
