package io.codething.sporttracker.infrastructure.repositories;

import io.codething.sporttracker.domain.entities.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityRepository {
    private final List<Activity> activities = new ArrayList<>();

    public void save(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> findByUserId(String userId) {
        return activities.stream()
                .filter(activity -> activity.userId().equals(userId))
                .collect(Collectors.toList());
    }
}
