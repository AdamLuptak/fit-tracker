package io.codething;

import static io.codething.User.Gender.*;

public class BMRCalculator {

    public double calculateBMR(User.Gender gender, int age, double weight, double height) {
        if (gender == MALE) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else if (gender == FEMALE) {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        } else {
            throw new IllegalArgumentException("Invalid gender. Please specify 'male' or 'female'.");
        }
    }
}
