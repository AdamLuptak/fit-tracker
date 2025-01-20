package io.codething.sporttracker.domain.calculators;


import io.codething.sporttracker.domain.User;

import static io.codething.sporttracker.domain.User.Gender.FEMALE;
import static io.codething.sporttracker.domain.User.Gender.MALE;

public class BMRCalculator {

    public double calculate(User user) {
        if (user.gender() == MALE) {
            return 10 * user.weight() + 6.25 * user.height() - 5 * user.age() + 5;
        } else if (user.gender() == FEMALE) {
            return 10 * user.weight() + 6.25 * user.height() - 5 * user.age() - 161;
        } else {
            throw new IllegalArgumentException("Invalid gender. Please specify 'male' or 'female'.");
        }
    }
}
