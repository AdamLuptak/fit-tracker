package io.codething;

import io.codething.calculators.CalorieModifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TrackerAppTest {
    private UserService userService;
    private TrackerApp trackerApp;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        trackerApp = new TrackerApp(userService);
    }

    @Test
    void testAddActivity_UserNotFound() {
        when(userService.findUser("nonexistentUser")).thenReturn(null);

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            trackerApp.addActivity("nonexistentUser", Activity.ActivityType.RUNNING, 30, 5, null);
        });

        assertEquals("User not found: nonexistentUser", exception.getMessage());
    }

    @Test
    void testAddActivity_WithModifiers() {
        var user = mock(User.class);
        when(userService.findUser("testUser")).thenReturn(user);

        var modifier = mock(CalorieModifier.class);
        List<CalorieModifier> modifiers = List.of(modifier);

        trackerApp.addActivity("testUser", Activity.ActivityType.RUNNING, 30, 5, modifiers);

        verify(user).addActivity(any(Activity.class));
    }

    @Test
    void testPrintUserSummary_UserNotFound() {
        when(userService.findUser("nonexistentUser")).thenReturn(null);

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            trackerApp.printUserSummary("nonexistentUser");
        });

        assertEquals("User not found: nonexistentUser", exception.getMessage());
    }

    @Test
    void testPrintUserSummary() {
        var user = mock(User.class);
        when(userService.findUser("testUser")).thenReturn(user);
        when(user.name()).thenReturn("testUser");
        when(user.activities()).thenReturn(List.of());

        trackerApp.printUserSummary("testUser");
    }
}