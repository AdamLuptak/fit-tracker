package io.rebase.fitnessapp.activity.application.command;

import io.rebase.fitnessapp.activity.domain.model.Distance;
import java.time.Duration;

public record CreateActivityCommand(
    String userId, String activityType, Duration duration, Distance distance) {}
