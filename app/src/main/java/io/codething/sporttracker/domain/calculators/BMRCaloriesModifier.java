package io.codething.sporttracker.domain.calculators;


public record BMRCaloriesModifier(double bmr) implements CaloriesModifier {

    @Override
    public double modify(double baseCalories, double duration, double distance) {
        var durationInHours = duration / 60.0;
        double bmrContribution = (bmr / 24) * durationInHours;
        return baseCalories + bmrContribution;
    }
}
