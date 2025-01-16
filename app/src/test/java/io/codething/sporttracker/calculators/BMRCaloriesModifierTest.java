package io.codething.sporttracker.calculators;

import io.codething.sporttracker.domain.calculators.BMRCaloriesModifier;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BMRCaloriesModifierTest {

    @Test
    void shouldCalculateCorrectBMR() {
        var modifier = new BMRCaloriesModifier(1000);
        var actualBMR = modifier.modify(70, 175, 25);
        assertThat(actualBMR).isCloseTo(191.52777777777777, Offset.offset(0.0001));
    }
}
