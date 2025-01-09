package io.codething.calculators;


public record BMRCalorieModifier (double bmr) implements CalorieModifier {

    @Override
    public double modify(double baseCalories, double duration, double distance) {
        var durationInHours = duration / 60.0;
        double bmrContribution = (bmr / 24) * durationInHours;
        return baseCalories + bmrContribution;
    }
}
