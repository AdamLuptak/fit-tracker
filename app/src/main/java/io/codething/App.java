package io.codething;

import io.codething.User.Gender;
import io.codething.calculators.BMRCalorieModifier;
import io.codething.calculators.CalorieModifier;

import java.util.List;

import static io.codething.User.Gender.FEMALE;

public class App {

    public static void main(String[] args) {
        var bmrCalculator = new BMRCalculator();
        var userService = new UserService();
        var trackerApp = new TrackerApp(userService);

        var alice = new User(bmrCalculator, FEMALE, "Alice", 21, 150,50); // BMR = 1500 calories/day
        var bob = new User(bmrCalculator, Gender.MALE, "Bob", 22, 180,90);   // BMR = 1800 calories/day

        userService.addUser(alice);
        userService.addUser(bob);

        var defaultModifiers = List.<CalorieModifier>of(new BMRCalorieModifier(alice.bmr()));
        trackerApp.addActivity(alice.name(), Activity.ActivityType.RUNNING, 30, 5.0,
                defaultModifiers);
        trackerApp.addActivity(alice.name(), Activity.ActivityType.SWIMMING, 30, 5.0,
                defaultModifiers);

        trackerApp.addActivity(bob.name(), Activity.ActivityType.WALKING, 60, 3.5, defaultModifiers);

        trackerApp.printUserSummary(alice.name());
        trackerApp.printUserSummary(bob.name());
    }
}
