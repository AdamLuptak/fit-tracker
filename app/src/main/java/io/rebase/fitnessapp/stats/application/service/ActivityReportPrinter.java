package io.rebase.fitnessapp.stats.application.service;

import io.rebase.fitnessapp.Application;
import io.rebase.fitnessapp.stats.domain.model.UserActivityReport;
import io.rebase.fitnessapp.usermanagement.domain.model.User;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityReportPrinter {
  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public void printAllActivities(User user, List<UserActivityReport> userActivityReports) {
    LOGGER.info("User: {}, Age: {}, Weight: {}", user.name(), user.age(), user.weight());
    for (var userActivityReport : userActivityReports) {

      LOGGER.info(
          "Activity: {}, Duration: {}, Distance: {}, BaseCalories: {}, modifiedCalories: {}",
          userActivityReport.activityType(),
          userActivityReport.duration(),
          userActivityReport.distance(),
          userActivityReport.baseCalories(),
          userActivityReport.modifiedCalories());
      LOGGER.info("Modifiers {}", String.join("", userActivityReport.calorieModifiers()));
    }
  }
}
