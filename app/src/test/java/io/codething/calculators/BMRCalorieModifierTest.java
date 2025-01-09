package io.codething.calculators;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BMRCalorieModifierTest {

    @Test
    void shouldCalculateCorrectBMR() {
        var modifier = new BMRCalorieModifier(1000);
        var actualBMR = modifier.modify(70, 175, 25);
        assertThat(actualBMR).isCloseTo(191.52777777777777, Offset.offset(0.0001));
    }
}
