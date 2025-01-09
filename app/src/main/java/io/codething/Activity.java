package io.codething;

class Activity {

    enum ActivityType {
        RUNNING,
        SWIMMING,
        WALKING;
    }

    private ActivityType type;
    private double duration; // in minutes
    private double distance; // in kilometers
    private double calories;

    public Activity(ActivityType type, double duration, double distance, double  calories) {
        this.type = type;
        this.duration = duration;
        this.distance = distance;
        this.calories = calories;
    }

    public double calculateCalories() {
        return calories;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "type=" + type +
                ", duration=" + duration +
                ", distance=" + distance +
                ", calories=" + calories +
                '}';
    }
}
