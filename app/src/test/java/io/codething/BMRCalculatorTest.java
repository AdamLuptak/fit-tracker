package io.codething;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class BMRCalculatorTest {
    @Test
    void shouldCalculateBMRForMale() {
        BMRCalculator bmrCalculator = new BMRCalculator();
        double bmr = bmrCalculator.calculateBMR(User.Gender.MALE, 25, 70, 180);
        assertThat(bmr).isEqualTo(1705.0);
    }

    @Test
    void shouldCalculateBMRForFemale() {
        BMRCalculator bmrCalculator = new BMRCalculator();
        double bmr = bmrCalculator.calculateBMR(User.Gender.FEMALE, 25, 70, 180);
        assertThat(bmr).isEqualTo(1539.0);
    }

    @Test
    void shouldThrowExceptionForInvalidGender() {
        BMRCalculator bmrCalculator = new BMRCalculator();
        assertThatThrownBy(() -> bmrCalculator.calculateBMR(null, 25, 70, 180))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid gender. Please specify 'male' or 'female'.");
    }

}