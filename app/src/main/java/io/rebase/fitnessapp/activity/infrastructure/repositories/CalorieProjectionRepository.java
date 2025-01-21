package io.rebase.fitnessapp.activity.infrastructure.repositories;

import io.rebase.fitnessapp.activity.domain.model.CalorieProjection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CalorieProjectionRepository {
  private final Map<String, CalorieProjection> projectionsByActivityId = new ConcurrentHashMap<>();
  private final Map<String, List<CalorieProjection>> projectionsByUserId =
      new ConcurrentHashMap<>();

  public void save(CalorieProjection projection) {
    projectionsByActivityId.put(projection.activity().id(), projection);

    projectionsByUserId
        .computeIfAbsent(projection.userId(), userId -> new ArrayList<>())
        .add(projection);
  }

  public void update(CalorieProjection projection) {
    // Update by Activity ID
    projectionsByActivityId.put(projection.activity().id(), projection);

    // Update in the User's List
    List<CalorieProjection> userProjections = projectionsByUserId.get(projection.userId());
    if (userProjections != null) {
      userProjections.removeIf(p -> p.activity().id().equals(projection.activity().id()));
      userProjections.add(projection);
    }
  }

  public Optional<CalorieProjection> findByActivityId(String activityId) {
    return Optional.ofNullable(projectionsByActivityId.get(activityId));
  }

  public Optional<CalorieProjection> findByUserIdAndActivityId(String userId, String activityId) {
    return Optional.ofNullable(projectionsByActivityId.get(activityId))
        .filter(p -> p.userId().equals(userId));
  }

  public List<CalorieProjection> findAllByUserId(String userId) {
    List<CalorieProjection> userProjections = projectionsByUserId.get(userId);
    if (userProjections == null || userProjections.isEmpty()) {
      return Collections.emptyList();
    }
    return userProjections;
  }
}
