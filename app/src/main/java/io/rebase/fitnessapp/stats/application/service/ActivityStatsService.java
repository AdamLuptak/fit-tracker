package io.rebase.fitnessapp.stats.application.service;

import io.rebase.fitnessapp.stats.application.query.UserActivitiesReportQuery;
import io.rebase.fitnessapp.stats.application.query.UserActivitiesReportUserCase;
import io.rebase.fitnessapp.stats.application.query.UserActivityReportQuery;
import io.rebase.fitnessapp.stats.application.query.UserActivityReportUserCase;
import io.rebase.fitnessapp.stats.domain.model.UserActivityReport;
import java.util.List;

public class ActivityStatsService {

  private final UserActivityReportUserCase userActivityReportUserCase;
  private final UserActivitiesReportUserCase userActivitiesReportUserCase;

  public ActivityStatsService(
      UserActivityReportUserCase userActivityReportUserCase,
      UserActivitiesReportUserCase userActivitiesReportUserCase) {
    this.userActivityReportUserCase = userActivityReportUserCase;
    this.userActivitiesReportUserCase = userActivitiesReportUserCase;
  }

  public List<UserActivityReport> getUserActivitiesReport(
      UserActivitiesReportQuery userActivitiesReportQuery) {
    return userActivitiesReportUserCase.handle(userActivitiesReportQuery);
  }

  public UserActivityReport getUserActivityReport(UserActivityReportQuery userActivityReportQuery) {
    return userActivityReportUserCase.handle(userActivityReportQuery);
  }
}
