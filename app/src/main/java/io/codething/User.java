package io.codething;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

record User(Gender gender, String name, int age, double weight, double height, double bmr, List<Activity> activities) {
    public enum Gender {MALE, FEMALE}

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    public User(BMRCalculator bmrCalculator, Gender gender, String name, int age, double weight, double height) {
        this(gender, name, age, weight, height, bmrCalculator.calculateBMR(gender, age, weight, height), new ArrayList<>());
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public int totalActivities() {
        return activities.size();
    }

    public int totalCalories() {
        return (int) activities.stream()
                .mapToDouble(Activity::calculateCalories)
                .sum();
    }

    public double totalDuration() {
        return activities.stream()
                .mapToDouble(Activity::getDuration)
                .sum();
    }

    @Override
    public String toString() {
        return String.format("User[name=%s, weight=%.2f, totalActivities=%d, totalCalories=%d, totalDuration=%.2f mins]",
                name, weight, totalActivities(), totalCalories(), totalDuration());
    }

}