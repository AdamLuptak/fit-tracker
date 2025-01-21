package io.rebase.fitnessapp.activity.domain.model;

import java.time.Duration;

public record Activity(
    String id, String userId, String type, Duration duration, Distance distance) {}
